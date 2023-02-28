<template>
  <div class="calculator-container">
    <div class="calculator-background">
      <div class="calculator">
        <div class="title">
          <h1>Calculator</h1>
        </div>
        <div class="display" id="result">
          {{ calculatorValue || 0 }}
        </div>
        <div class="buttons">
          <div class="row" v-for="n in calculatorElements" v-bind:key="n">
            <div
              class="text-white bg-vue-dark button bold"
              :class="{
                'bg-vue-orange': ['/', '*', '-', '+', '='].includes(n.value),
                'bg-vue-gray text-black': ['AC', '+/-', '%'].includes(n.value),
              }"
              @click="action(n.value)"
              :id="`button-${n.name}`"
            >
              {{ n.value }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div v-if="showLog" class="log-container">
    <h2>Calculation Log</h2>
    <div class="log">
      <p
        v-for="(result, index) in resultLog.slice(-10)"
        :key="index"
        :id="'log-item-' + index"
        class="log-item"
      >
        <span class="log-index">{{ index + 1 }}. </span>{{ result }}
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "CalculatorComponent",
  props: {
    msg: String,
  },
  data() {
    return {
      calculatorValue: "",
      calculatorElements: [
        { name: "AC", value: "AC" },
        { name: "sign-change", value: "+/-" },
        { name: "percentage", value: "%" },
        { name: "divide", value: "/" },
        { name: "7", value: 7 },
        { name: "8", value: 8 },
        { name: "9", value: 9 },
        { name: "multiply", value: "*" },
        { name: "4", value: 4 },
        { name: "5", value: 5 },
        { name: "6", value: 6 },
        { name: "subtract", value: "-" },
        { name: "1", value: 1 },
        { name: "2", value: 2 },
        { name: "3", value: 3 },
        { name: "add", value: "+" },
        { name: "0", value: 0 },
        { name: "comma", value: "." },
        { name: "square-root", value: "√" },
        { name: "equals", value: "=" },
      ],

      operator: null,
      prevCalculatorValue: "",
      resultLog: [],
      showLog: false,
      calculationLog: [],
      backendUrl: "http://localhost:8080/api/calculate",
    };
  },
  methods: {
    handleNumberInput(n) {
      if (this.calculatorValue === "" && n === ".") {
        this.calculatorValue = 0;
      } else if (n === "." && this.calculatorValue.includes(".")) {
        return;
      } else {
        this.calculatorValue += n + "";
      }
    },
    handleAc() {
      this.calculatorValue = "";
      this.prevCalculatorValue = "";
    },
    handlePercent() {
      this.calculatorValue = this.calculatorValue / 100 + "";
    },
    handleSignChange() {
      if (this.calculatorValue === "") {
        this.calculatorValue = "";
      } else {
        this.calculatorValue = -this.calculatorValue + "";
      }
    },
    handleOperator(n) {
      this.operator = n;
      this.prevCalculatorValue = this.calculatorValue;
      this.calculatorValue = "";
    },
    handleSquareRoot() {
      this.calculatorValue = Math.sqrt(this.calculatorValue).toFixed(2) + "";
    },
    handleEqual() {
      const equation = {
        operand1: this.prevCalculatorValue,
        operator: this.operator,
        operand2: this.calculatorValue,
      };
      axios
        .post(this.backendUrl, equation)
        .then((response) => {
          console.log(response.data);
          const calculation = `${equation.operand1} ${equation.operator} ${equation.operand2} = ${response.data}`;
          this.resultLog.push(calculation);
          this.calculatorValue = response.data;
          this.prevCalculatorValue = "";
          this.operator = null;
          this.showLog = true;
        })
        .catch((error) => {
          console.error(error);
        });
    },

    action(n) {
      if (!isNaN(n) || n === ".") {
        this.handleNumberInput(n);
      } else if (n === "AC") {
        this.handleAc();
      } else if (n === "%") {
        this.handlePercent();
      } else if (n === "+/-") {
        this.handleSignChange();
      } else if (n === "+" || n === "-" || n === "*" || n === "/") {
        this.handleOperator(n);
      } else if (n === "√") {
        this.handleSquareRoot();
      } else if (n === "=") {
        this.handleEqual();
      }
    },
  },
};
</script>

<style scoped>
.calculator-container {
  position: relative;
  margin-top: 50px;
}

.calculator-background {
  background-color: #dfbf9f;
  border-radius: 30px;
  padding: 30px;
  width: 500px;
  height: 420px;
  margin: 0 auto;
}

.calculator {
  margin: 0 auto;
  width: 500px;
  height: 400px;
  text-align: center;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: auto 1fr;
  grid-template-areas: "title title title title" "display display display display" "buttons buttons buttons buttons";
  gap: 5px;
  background-color: #dfbf9f;
}

.title {
  grid-area: title;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Courier New", Courier, monospace;
  color: black;
}

.display {
  grid-area: display;
  background-color: rgb(114, 112, 112);
  padding: 10px;
  text-align: right;
  border-radius: 10px;
  color: white;
  padding-top: 20px;
  padding-right: 25px;
  font-size: 30px;
}

.buttons {
  grid-area: buttons;
  display: grid;
  grid-template-rows: repeat(5, 1fr);
  grid-template-columns: repeat(4, 1fr);
  gap: 5px 5px;
}

.button {
  width: 100%;
  height: 35px;
  border-radius: 10px;
  padding-top: 15px;
}

.buttons .button {
  border-radius: 10px;
}

.text-white {
  color: white;
}

.text-black {
  color: black;
}

.bg-vue-orange {
  background-color: orange;
}

.bg-vue-gray {
  background-color: lightgray;
}

.bg-vue-dark {
  background-color: #333;
}

.bg-vue-dark[class*="bg-vue-orange"],
.bg-vue-orange[class*="bg-vue-dark"] {
  background-color: orange;
}

.bg-vue-dark[class*="bg-vue-gray"],
.bg-vue-gray[class*="bg-vue-dark"] {
  background-color: lightgray;
}

.log-container {
  margin-top: 25px;
  text-align: center;
  padding: 0 20px;
}

.log-container,
h2 {
  font-family: "Courier New", Courier, monospace;
}

.bold {
  font-weight: bold;
}

.log {
  background-color: #dfbf9f;
  padding: 10px;
  border-radius: 10px;
  max-width: 400px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: scroll;
  overflow-x: hidden;
  max-height: 200px;
}

.log p {
  display: flex;
  justify-content: space-between;
  width: 100%;
  font-size: 18px;
}

.log::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

.log::-webkit-scrollbar-thumb {
  background-color: #9f72457d;
  border-radius: 0 45px 45px 0;
}

.log::-webkit-scrollbar-thumb:hover {
  background-color: #9f7245;
}

.log::-webkit-scrollbar-track {
  display: none;
}

.log-item {
  margin: 5px;
  display: flex;
  align-items: center;
}

.log-index {
  font-weight: bold;
  margin-right: 10px;
  text-align: right;
  width: 20px;
}
</style>
