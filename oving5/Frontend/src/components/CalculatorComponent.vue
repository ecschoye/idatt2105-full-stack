<template>
  <div
    class="bg-title title-color h-16 rounded-xl text-white font-bold text-2xl mx-auto mt-20 w-2/5 text-center pt-4"
  >
    <p>Calculator</p>
  </div>
  <div
    class="bg-black rounded-lg w-2/5 mx-auto mt-5 min-w-[400px] max-w-[600px] min-h-[500px] max-h-[700px]"
  >
    <div class="calculator w-11/12 mx-auto mt-30">
      <div
        class="bg-gray-700 h-24 rounded-xl w-100 mx-auto mt-5 mb-5 text-right pt-6"
        id="result"
      >
        <p class="text-white text-4xl mt-1 pr-10">{{ calculatorValue || 0 }}</p>
      </div>
      <div class="buttons mb-5">
        <div class="" v-for="n in calculatorElements" v-bind:key="n">
          <div
            class="text-white bg-vue-dark button bold rounded-lg w-30 h-16 mx-auto text-center align-bottom pt-5"
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
  <div v-if="resultLog.length > 0" class="log-container">
    <h2 class="text-2xl mt-5">Calculation Log</h2>
    <div class="log">
      <p
        v-for="(log, index) in resultLog.slice(-10)"
        :key="index"
        :id="'log-item-' + index"
        class="log-item"
        @click="setCalculatorValue(log)"
      >
        <span class="log-index">{{ index + 1 }}. </span>{{ log }}
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { useTokenStore } from "@/store/token";

export default {
  name: "CalculatorComponent",
  props: {
    msg: String,
  },
  setup() {
    console.log("CalculatorComponent setup");
    const tokenStore = useTokenStore();

    function updateToken() {
      console.log("Updating token");
      console.log(tokenStore.loggedInUser.username);
      console.log(tokenStore.loggedInUser.password);
      tokenStore
        .getTokenAndSaveInStore(
          tokenStore.loggedInUser.username,
          tokenStore.loggedInUser.password
        )
        .then((token) => {
          console.log("Token updated");
          console.log(token);
        });
    }
    updateToken();

    setInterval(() => {
      updateToken();
      console.log("Token refreshed");
    }, 1000 * 60 * 5);

    return { tokenStore };
  },
  async mounted() {
    if (!this.tokenStore.jwtToken) {
      console.log("Unauthenticated context");
    } else {
      console.log("Authenticated context");
    }
  },
  async created() {
    const equation = {
      operand1: this.prevCalculatorValue,
      operator: this.operator,
      operand2: this.calculatorValue,
      equation: "",
      result: "",
      user: {
        username: this.tokenStore.loggedInUser.username,
        password: "",
      },
    };

    const config = {
      headers: {
        "Content-type": "application/json",
        Authorization: "Bearer " + this.tokenStore.jwtToken,
      },
    };

    const equations = await (
      await axios.post(
        "http://localhost:8080/calculator/getCalculations",
        equation,
        config
      )
    ).data;
    console.log(equations);
    this.resultLog = equations;
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
      backendUrl: "http://localhost:8080/calculate",
    };
  },
  methods: {
    handleNumberInput(n) {
      if (this.calculatorValue === "" && n === ".") {
        this.calculatorValue = 0;
      } else if (n === "." && this.calculatorValue.includes(".")) {
        return;
      } else if (this.calculatorValue === "0") {
        this.calculatorValue = n + "";
      } else {
        this.calculatorValue += n + "";
      }
    },
    handleAc() {
      this.calculatorValue = "0";
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
      this.calculatorValue = "0";
    },
    handleSquareRoot() {
      if (this.calculatorValue === "") {
        this.calculatorValue = "";
      }
      if (this.calculatorValue < 0) {
        return;
      }
      this.calculatorValue = Math.sqrt(this.calculatorValue).toFixed(2) + "";
    },
    async handleEqual() {
      if (this.operator === null || this.prevCalculatorValue === "") {
        return;
      }
      await this.calculate();
      await this.getLog();
    },

    async calculate() {
      console.log("calculate function");
      console.log(this.tokenStore.loggedInUser.username);
      const equation = {
        operand1: this.prevCalculatorValue,
        operator: this.operator,
        operand2: this.calculatorValue,
        equation: "",
        result: "",
        user: {
          username: this.tokenStore.loggedInUser.username,
          password: "",
          equations: [],
        },
      };
      console.log("before axios");
      console.log(equation);
      console.log("-----------------");
      console.log(this.tokenStore.jwtToken);

      const config = {
        headers: {
          "Content-type": "application/json",
          Authorization: "Bearer " + this.tokenStore.jwtToken,
        },
      };

      console.log(equation);
      console.log(config);
      this.calculatorValue = await (
        await axios.post(
          "http://localhost:8080/calculator/calculate",
          equation,
          config
        )
      ).data;
    },
    async getLog() {
      const equation = {
        operand1: this.prevCalculatorValue,
        operator: this.operator,
        operand2: this.calculatorValue,
        equation: "",
        result: "",
        user: {
          username: this.tokenStore.loggedInUser.username,
          password: "",
        },
      };

      const config = {
        headers: {
          "Content-type": "application/json",
          Authorization: "Bearer " + this.tokenStore.jwtToken,
        },
      };

      const equations = await (
        await axios.post(
          "http://localhost:8080/calculator/getCalculations",
          equation,
          config
        )
      ).data;
      console.log(equations);
      this.resultLog = equations;
    },
    setCalculatorValue(value) {
      console.log("setCalculatorValue");
      console.log(value);
      const result = value.split("=")[1].trim(); // extract the value after the '=' sign
      this.calculatorValue = result;
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
/*
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
*/

/*
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
*/

.buttons {
  grid-area: buttons;
  display: grid;
  grid-template-rows: repeat(5, 1fr);
  grid-template-columns: repeat(4, 1fr);
  gap: 5px 5px;
}

/*
.button {
  width: 100%;
  height: 35px;
  border-radius: 10px;
  padding-top: 15px;
}
*/

.buttons .button {
  border-radius: 10px;
}

.text-white {
  color: white;
}

.text-black {
  color: black;
}

.title-color {
  color: #9f7245;
}

.bg-title {
  background-color: #dfbf9f;
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
  max-height: fit-content;
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
