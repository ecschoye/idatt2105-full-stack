<template>
    <div class="card text-center">
        <h1>Calculator</h1>
    </div>
    <div class="w-2/5 m-8 mx-auto">
        <div class="flex flex-col bg-gray-800 rounded-lg">
            <div class="w-full h-20 flex items-center bg-gray-700 justify-end rounded-t-lg">
                <p class="text-white text-right text-2xl px-4 font-bold">{{ display || 0 }}</p>
            </div>
            <div class="m-4 flex flex-col flex-grow">
                <div class="flex justify-center mb-1">
                    <Button button="AC" class="mr-1 w-20 rounded bg-gray-200 text-gray-800 text-center flex-grow"/>
                    <Button button="+/-" class="mr-1 w-20 rounded bg-gray-200 text-gray-800 text-center flex-grow"/>
                    <Button button="%" class="mr-1 w-20 rounded bg-gray-200 text-gray-800 text-center flex-grow"/>
                    <Button button="/" @click="addOperator('/')" class="mr-1 w-20 rounded bg-orange-400 text-white text-center flex-grow flex-grow"/>
                </div>
                <div class="flex justify-center mb-1">
                    <Button button="7" @click="addInteger('7')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="8" @click="addInteger('8')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="9" @click="addInteger('9')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="*" @click="addOperator('*')" class="mr-1 w-20 rounded bg-orange-400 text-white text-center flex-grow"/>
                </div>
                <div class="flex justify-center mb-1">
                    <Button button="4" @click="addInteger('4')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="5" @click="addInteger('5')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="6" @click="addInteger('6')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="-" @click="addOperator('-')" class="mr-1 w-20 rounded bg-orange-400 text-white text-center flex-grow"/>
                </div>
                <div class="flex justify-center mb-1">
                    <Button button="1" @click="addInteger('1')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="2" @click="addInteger('2')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="3" @click="addInteger('3')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="+" @click="addOperator('+')" class="mr-1 w-20 rounded bg-orange-400 text-white text-center flex-grow"/>
                </div>
                <div class="flex justify-center">
                    <Button button="0" class="mr-2 w-2/5 rounded bg-gray-600 text-white flex-grow"/>
                    <Button button="." class="mr-1 w-20 rounded bg-gray-600 text-white text-center flex-grow"/>
                    <Button button="=" class="mr-1 w-20 rounded bg-orange-400 text-white text-center flex-grow"/>
                </div>
            </div>
        </div>
    </div>
    
</template>

<script>
import axios from "axios";
import { createStore } from 'vuex';

export default {
    data() {
        return {
            display: '',
            calculatorValue: '',
            operator: '',
            prevCalculation: '',
            resultLog: [],
            calculationLog: [],
            backendUrl: "http://localhost:8080/api/calculate",
        }
    },
    methods: {
        addInteger(integer) {
            if (this.operator === '') {
                this.firstNumber += integer
                this.display = this.firstNumber
            } else {
                this.secondNumber += integer
                this.display = this.secondNumber
                this.$store.commit('SET_DISPLAY', this.secondNumber)
            }
        },
        addOperator(operator) {
            this.operator = operator
        },
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
    }
}
</script>



<style scoped>

</style>