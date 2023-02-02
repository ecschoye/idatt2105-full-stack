<template>
    <div class="bg-vue-light-gray">
        <div class="p-3 main rounded">
        <div class="w-full rounded m-2 p-4 display-6 text-end font-weight-bold text-white bg-vue-dark hover">
            {{ calculatorValue || 0 }}
        </div>
        <div class="row g-0 parent">
            <div class="col-3" v-for="n in calculatorElements" v-bind:key="n">
                <div class=" text-white text-center m-1 py-2 bg-vue-dark"
                    :class="{'bg-vue-orange': ['/', '*', '-', '+','='].includes(n), 'bg-vue-gray': ['AC', '+/-', '%'].includes(n)}"
                    @click="action(n)"
                >
                    {{ n }}
                </div>
            </div>
        </div>
    </div>
    <div class="p-3 text-center log">
        <div class="text-white rounded">
            <p v-show="action">Calculator log</p>
        </div>
        <span style="white-space: pre-line">{{ resultLog }}</span>
        
    </div>
    </div>
</template>

<script>
    let bool = 0
    let num1;
    let num2;
    let operator;
    export default {
        name: 'Calculator',
        props: {
            msg: String
        },

        data() {
        return {
            calculatorValue: '',
            calculatorElements: ['AC', '+/-', '%', '/',
                                7, 8, 9, '*', 
                                4, 5, 6, '-', 
                                1, 2, 3, '+',
                                0, '.', '√', '='],
            operator: null,
            prevCalculatorValue: '',
            resultLog: ''
        }
    },

    methods: {
        action(n) {
            if (this.calculatorValue.includes('.')) {
                this.calculatorValue = this.calculatorValue;
                bool = 1;
            }
            if(!isNaN(n) || n == '.' && bool != 1) {
                if (this.calculatorValue === '' && n == '.') {
                    this.calculatorValue = 0;
                }
                this.calculatorValue += n + "";
            }
            if (n === 'AC') {
                this.calculatorValue = '';
                this.prevCalculatorValue = ''
                this.resultLog = '';
            }
            if (n === '%') {
                this.calculatorValue = this.calculatorValue/100 + "";
            }
            if (n === '+/-') {
                if (this.calculatorValue === '') {
                    this.calculatorValue = "";
                } else {
                    this.calculatorValue = -this.calculatorValue + "";
                }
            }
            if (n === '+' || n === '-' || n === '*' || n === '/') {
                this.operator = n;
                this.prevCalculatorValue = this.calculatorValue;
                this.calculatorValue = "";
                bool = 0
            }
            if (n === '√') {
                this.calculatorValue = Math.sqrt(this.calculatorValue).toFixed(2) + "";
            }
            
            if (n === '=') {
                if (this.prevCalculatorValue === '' && this.calculatorValue !== '') {
                    alert("You have to choose more than one number");
                } else if (this.prevCalculatorValue === '' && this.calculatorValue === ''){
                    alert("You have to choose a number");
                } else {
                    this.resultLog += this.prevCalculatorValue + " " + this.operator + " " + this.calculatorValue + " = ";
                    this.calculatorValue = eval(
                        this.prevCalculatorValue + this.operator + this.calculatorValue
                    );
                    this.calculatorValue = this.calculatorValue.toFixed(2);
                    this.resultLog += this.calculatorValue + "\n";
                    this.calculatorValue += '';
                    this.prevCalculatorValue = '';
                    this.operator = null;
                    this.resultLog;
                }
                
            }
            
           /*
            if (n === '=') {
                this.resultLog += this.prevCalculatorValue + " " + this.operator + " " + this.calculatorValue + " = ";
                this.calculatorValue = eval(
                    this.prevCalculatorValue + this.operator + this.calculatorValue
                );
                if (this.calculatorValue % 1 !== 0) {
                  this.resultLog += parseFloat(this.calculatorValue).toFixed(2) + "\n";
                  this.calculatorValue = parseFloat(this.calculatorValue).toFixed(2) + "";
                } else {
                  this.resultLog += this.calculatorValue + "\n";
                }
                this.prevCalculatorValue = '';
                this.operator = null;
            }
            */
            
        }
    }

}
</script>

<style scoped>

.main {
    max-width: 400px;
    margin: 50px auto;
    background: black;
}

.log {
    max-width: 400px;
    margin: auto;
    background: gray;
}

.bg-vue-dark {
    background: gray;
}

.hover-class:hover {
    cursor: pointer;
    background: #3D5875;
}

.bg-vue-orange {
    background: #da9a41;
}

.bg-vue-gray {
    background: #aeadab;
}

.bg-vue-light-gray {
    background: rgb(222, 222, 222);
}

</style>