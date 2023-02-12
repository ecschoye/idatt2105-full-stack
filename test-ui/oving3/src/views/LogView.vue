<template>
  <div class="log-container"></div>
</template>

<script>
export default {
  name: "LogView",
  props: {
    log: {
      type: Array,
      default: () => [],
    },
  },
};
</script>

<style scoped>
.log-container {
  position: relative;
  margin-top: 50px;
}

.log-background {
  background-color: lightblue;
  border-radius: 30px;
  padding: 30px;
  width: 500px;
  height: 200px;
  margin: 0 auto;
}

.log {
  margin: 0 auto;
  width: 500px;
  height: 200px;
  text-align: center;
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  grid-template-rows: auto 1fr;
  grid-template-areas: "title" "log-display";
  gap: 5px;
  background-color: lightblue;
}

.title {
  grid-area: title;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Courier New", Courier, monospace;
  color: black;
}

.log-display {
  grid-area: log-display;
  background-color: rgb(114, 112, 112);
  padding: 10px;
  text-align: left;
  border-radius: 10px;
  color: white;
  padding-top: 20px;
  padding-left: 25px;
  font-size: 15px;
  overflow-y: scroll;
  height: 150px;
}
</style>
