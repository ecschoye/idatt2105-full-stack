<template>
  <div class="calculator-container">
    <div class="calculator-background">
      <div class="calculator">
        <div class="title">
          <h1>Calculator</h1>
        </div>
        <div class="display">
          {{ calculatorValue || 0 }}
        </div>
        <div class="buttons">
          <div class="row" v-for="n in calculatorElements" v-bind:key="n">
            <div
              class="text-white bg-vue-dark button"
              :class="{
                'bg-vue-orange': ['/', '*', '-', '+', '='].includes(n),
                'text-black': ['AC', '+/-', '%'].includes(n),
              }"
              @click="action(n)"
            >
              {{ n }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CalculatorComponent",
  props: {
    msg: String,
  },
  data() {
    return {
      calculatorValue: "",
      calculatorElements: [
        "AC",
        "+/-",
        "%",
        "/",
        7,
        8,
        9,
        "*",
        4,
        5,
        6,
        "-",
        1,
        2,
        3,
        "+",
        0,
        ".",
        "√",
        "=",
      ],
      operator: null,
      prevCalculatorValue: "",
      resultLog: "",
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
      this.resultLog = "";
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
      if (this.prevCalculatorValue === "" && this.calculatorValue !== "") {
        alert("You have to choose more than one number");
      } else if (
        this.prevCalculatorValue === "" &&
        this.calculatorValue === ""
      ) {
        alert("You have to choose a number");
      } else {
        this.resultLog +=
          this.prevCalculatorValue +
          " " +
          this.operator +
          " " +
          this.calculatorValue +
          " = ";
        this.calculatorValue = eval(
          this.prevCalculatorValue + this.operator + this.calculatorValue
        );
        this.calculatorValue = this.calculatorValue.toFixed(2);
        this.resultLog += this.calculatorValue + "\n";
        this.calculatorValue += "";
        this.prevCalculatorValue = "";
        this.operator = null;
      }
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
  background-color: lightblue;
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
  height: 35px; /* increased height */
  border-radius: 10px;
  padding-top: 15px; /* added padding-top */
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
</style>
