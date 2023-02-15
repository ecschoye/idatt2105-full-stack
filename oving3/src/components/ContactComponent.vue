<template>
  <div class="form-container">
    <div class="title">
      <h2>Form</h2>
    </div>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="name">Name</label>
        <input
          type="text"
          id="name"
          placeholder="Name"
          class="form-control"
          v-model="formData.name"
          @input="updateName"
          required
        />
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input
          type="email"
          id="email"
          placeholder="E-mail"
          class="form-control"
          v-model="formData.email"
          @input="updateEmail"
          required
        />
      </div>
      <div class="form-group">
        <label for="feedback">Feedback</label>
        <textarea
          id="feedback"
          class="form-control"
          placeholder="Feedback"
          v-model="formData.feedback"
          required
        ></textarea>
      </div>
      <div class="form-group">
        <div class="button">
          <button
            type="submit"
            class="btn btn-primary"
            :disabled="isFormIncomplete"
            :class="{ disabled: isFormIncomplete }"
            @click.prevent="submitForm"
          >
            Submit
          </button>
        </div>
        <div class="success-text" v-if="success">{{ successText }}</div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

var validEmailRegex =
  /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z]{1,})$/;
var validNameRegex = /^[a-zA-Z -]+$/;

export default {
  data() {
    return {
      formData: {
        name: "",
        email: "",
        feedback: "",
      },
      success: false,
      successText: "Success!",
    };
  },
  methods: {
    getData() {
      this.$store.commit("SET_NAME", this.formData.name);
      this.$store.commit("SET_EMAIL", this.formData.email);
    },
    async submitForm() {
      this.getData();
      axios
        .post("http://localhost:3000/form", this.formData)
        .then(() => {
          this.formData.name = "";
          this.formData.email = "";
          this.formData.feedback = "";
          this.success = true;
          setTimeout(() => {
            this.success = false;
          }, 3000);
        })
        .catch(() => {
          alert("Something went wrong");
        });
    },

    updateName(event) {
      this.$store.commit("SET_NAME", event.target.value);
    },
    updateEmail(event) {
      this.$store.commit("SET_EMAIL", event.target.value);
    },

    validForm() {
      return (
        this.validateName() && this.validateEmail() && this.validateFeedback()
      );
    },

    validateName() {
      return validNameRegex.test(this.formData.name);
    },

    validateEmail() {
      return validEmailRegex.test(this.email);
    },

    validateFeedback() {
      return this.feedback.length > 0 && !this.isOnlyWhitespace(this.feedback);
    },

    isOnlyWhitespace(str) {
      return str.trim().length === 0;
    },
  },
  computed: {
    isFormIncomplete() {
      return (
        !this.formData.name ||
        !this.formData.email ||
        !this.formData.feedback ||
        !validEmailRegex.test(this.formData.email) ||
        !validNameRegex.test(this.formData.name)
      );
    },
  },
};
</script>
<style scoped>
.title {
  text-align: center;
  font-size: 20px;
}
.form-container {
  font-family: "Courier New", Courier, monospace;
  max-width: 700px;
  width: 100%;
  max-height: 500px;
  height: 500px;
  background: #dfbf9f;
  padding: 20px;
  border-radius: 20px;
  margin: 50px auto;
  overflow: auto;
}

.form-group {
  margin-top: 20px;
  width: 80%;
  margin: 0 auto;
  margin-top: 20px;
}

label {
  font-weight: bold;
}

input,
textarea {
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  border-radius: 5px;
  border: 1px solid lightgray;
  resize: none;
}

#feedback {
  height: 75px;
}

.button {
  color: #4d3319;
}

button[type="submit"] {
  width: 100%;
  padding: 10px;
  margin-top: 20px;
  background-color: #cc9966;
  color: #4d3319;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
}

.disabled {
  opacity: 0.1;
  cursor: not-allowed;
  pointer-events: none;
}
.success-text {
  text-align: center;
  font-family: "Courier New", Courier, monospace;
  margin-top: 20px;
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
}
</style>
