<template>
  <div v-if="showLog" class="log-container">
    <h2>Calculation Log Wow</h2>
    <div class="log" v-html="formattedResultLog"></div>
  </div>
</template>

<script>
export default {
  name: "LogComponent",
  props: {
    resultLog: {
      type: String,
      required: true,
    },
  },
  computed: {
    formattedResultLog() {
      return this.resultLog.replace(/\n/g, "<br>");
    },
  },
};
</script>

<style>
.log-container {
  margin-top: 25px;
  text-align: center;
}

.log-container,
h2 {
  font-family: "Courier New", Courier, monospace;
}

.log {
  background-color: #dfbf9f;
  padding: 10px;
  border-radius: 10px;
  max-width: 400px;
  margin: 0 auto;
}
</style>
