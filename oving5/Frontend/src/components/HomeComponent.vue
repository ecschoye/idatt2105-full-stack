<template>
  <template v-if="user">
    <Navbar />
  </template>
  <div class="home">
    <h1>Welcome to the home page of Edvard's IDATT2105 exercise</h1>
  </div>
  <div class="main">
    <p v-if="user" id="login-success">Hello {{ this.username }}</p>
    <div v-if="!user" class="login-section">
      <div id="username">
        <input
          class="pl-3"
          type="text"
          v-model="username"
          placeholder="Username"
        />
      </div>
      <div id="password">
        <input
          class="pl-3"
          type="password"
          v-model="password"
          placeholder="Password"
        />
      </div>
      <div id="login">
        <button @click="login()">Login</button>
      </div>
      <div id="register">
        <button @click="register()">Register</button>
      </div>
      <p id="error">
        {{ error }}
      </p>
      <p class="pl-3" id="loginInfo">
        {{ loginInfo }}
      </p>
    </div>
    <div v-if="user" id="logout">
      <button @click="logout()">Logout</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { useTokenStore } from "@/store/token";
import { getUserInfo } from "/httputils.js";
import Navbar from "./NavbarComponent.vue";

export default {
  name: "HomeComponent",
  props: {
    msg: String,
  },
  components: {
    Navbar,
  },
  setup() {
    const tokenStore = useTokenStore();
    return { tokenStore };
  },
  async mounted() {
    if (!this.tokenStore.jwtToken) {
      console.log("Unauthenticated context");
    } else {
      console.log("Authenticated context");
      console.log(this.tokenStore.jwtToken);
      console.log("Logged in user: ");
      console.log(this.tokenStore.loggedInUser);
      let response = await getUserInfo(
        this.tokenStore.loggedInUser.username,
        this.tokenStore.loggedInUser.password,
        this.tokenStore.jwtToken
      );
      console.log("response from getUserInfo");
      console.log(response);
      this.user = response.data;
      this.username = this.tokenStore.loggedInUser.username;
    }
  },

  data() {
    return {
      username: "",
      password: "",
      loginInfo: "",
      error: "",
      user: null,
    };
  },
  methods: {
    async login() {
      try {
        await this.tokenStore.getTokenAndSaveInStore(
          this.username,
          this.password
        );
        console.log(this.username);
        if (this.tokenStore.jwtToken) {
          this.$router.push("calculator");
          this.error = "";
        } else {
          this.error = "Login failed!";
        }
      } catch (error) {
        console.error(error); // log the error for debugging purposes
        this.error = "An error occurred while logging in.";
      }
    },
    async logout() {
      this.tokenStore.jwtToken = null;
      this.tokenStore.loggedInUser = null;
      this.tokenStore.loggedInUsername = null;
      this.tokenStore.loggedInUserpassword = null;
      this.user = null;
      this.username = null;
      this.loginInfo = "You are logged out";
    },
    async register() {
      const user = {
        username: this.username,
        password: this.password,
      };
      console.log(user);
      this.access = await (
        await axios.post("http://localhost:8080/user/createuser", user)
      ).data;

      if (this.access === true) {
        console.log("User was created");
        this.error = "User was created";
      } else {
        this.error = "Error trying to create a new user";
        console.log("Error trying to create a new user");
      }
    },
  },
};
</script>

<style scoped>
.home {
  padding-top: 20px;
  font-family: "Courier New", Courier, monospace;
  font-size: 25px;
  background-color: #dfbf9f;
  max-width: 550px;
  max-height: 200px;
  margin: 40px auto;
  border-radius: 10px;
  text-align: center;
}

.main {
  padding-top: 20px;
  font-family: "Courier New", Courier, monospace;
  font-size: 25px;
  font-weight: bold;
  background-color: #dfbf9f;
  max-width: 550px;
  width: 400px;
  max-height: fit-content;
  margin: 10px auto;
  border-radius: 10px;
}

#username {
  margin: 10px;
}

#password {
  margin: 10px;
}

#login {
  margin: 10px;
  background-color: #9f7245;
  border-radius: 10px;
  padding-left: 20px;
  color: white;
}

#login-success {
  margin: 10px auto;
  border-radius: 10px;
  font-size: 40px;
  padding-left: 20px;
  max-width: fit-content;
}

#logout {
  text-align: center;
  margin: 50px auto;
  background-color: #9f7245;
  border-radius: 10px;
  color: white;
  width: fit-content;
  padding: 0 20px;
}

#register {
  margin: 10px;
  background-color: #9f7245;
  border-radius: 10px;
  padding-left: 20px;
  color: white;
}
</style>
