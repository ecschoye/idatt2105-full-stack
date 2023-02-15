<template>
  <div class="form-container">
    <div class="title">
      <h2>Form</h2>
    </div>
    <form @submit.prevent = "submitForm">
      <div class="form-group">
        <label for="name">Name</label>
        <input
          type="text"
          id="name"
          placeholder="Ola Olsen"
          class="form-control"
          v-model="name"
          required
        />
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input
          type="email"
          id="email"
          placeholder="ola@olsen.com"
          class="form-control"
          v-model="email"
          required
        />
      </div>
      <div class="form-group">
        <label for="feedback">Feedback</label>
        <textarea
          id="feedback"
          class="form-control"
          placeholder="Jeg synes ..."
          v-model="feedback"
          required
        ></textarea>
      </div>
      <div class="form-group">
        <div class="button">
          <button
            type="submit"
            class="btn btn-primary"
            @click.prevent="submitForm"
          >
            Submit
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
var validRegex =
  /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
export default {
  data() {
    return {
      name: "",
      email: "",
      feedback: "",
    };
  },
  methods: {
    submitForm() {
      if (this.name === "" || this.email === "" || this.feedback === "") {
        alert("Please fill out all fields");
      } else if (!validRegex.test(this.email)) {
        alert("Please enter a valid email");
      } else {
        alert(
          "Form submitted successfully!\nName: " +
            this.name +
            "\nEmail: " +
            this.email +
            "\nFeedback: " +
            this.feedback
        );
        this.name = "";
        this.email = "";
        this.feedback = "";
      }
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
  background: #dfbf9f;
  padding: 20px;
  border-radius: 20px;
  margin: 50px auto;
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
</style>
