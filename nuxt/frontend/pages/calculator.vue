<template>
    <div class="card text-center">
        <h1>Calculator</h1>
    </div>
    <div class="max-w-min m-8 mx-auto">
        <div class="flex flex-col bg-gray-800 rounded-lg">
            <div class="w-full h-16 flex items-center bg-gray-700 justify-end rounded-t-lg">
                <p class="text-white text-right px-4 font-bold">{{ display || 0 }}</p>
            </div>
            <div class="m-4 flex flex-col">
                <div class="flex justify-center mb-2">
                    <Button button="AC" class="mr-1 w-20 rounded bg-gray-200 text-gray-800 text-center"/>
                    <Button button="+/-" class="mr-1 w-20 rounded bg-gray-200 text-gray-800 text-center"/>
                    <Button button="%" class="mr-1 w-20 rounded bg-gray-200 text-gray-800 text-center"/>
                    <Button button="/" @click="addOperator('/')" class="mr-1 w-20 rounded bg-orange-400 text-white text-center"/>
                </div>
                <div class="flex justify-center mb-1">
                    <Button button="7" @click="addInteger('7')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="8" @click="addInteger('8')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="9" @click="addInteger('9')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="*" @click="addOperator('*')" class="mr-1 w-20 rounded bg-orange-400 text-white text-center"/>
                </div>
                <div class="flex justify-center mb-1">
                    <Button button="4" @click="addInteger('4')" class="mr-1 w-20 h-8 rounded bg-gray-600 text-white text-center"/>
                    <Button button="5" @click="addInteger('5')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="6" @click="addInteger('6')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="-" @click="addOperator('-')" class="mr-1 w-20 rounded bg-orange-400 text-white text-center"/>
                </div>
                <div class="flex justify-center mb-1">
                    <Button button="1" @click="addInteger('1')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="2" @click="addInteger('2')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="3" @click="addInteger('3')" class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="+" @click="addOperator('+')" class="mr-1 w-20 rounded bg-orange-400 text-white text-center"/>
                </div>
                <div class="flex justify-center">
                    <Button button="0" class="mr-2 w-40 rounded bg-gray-600 text-white text-left"/>
                    <Button button="." class="mr-1 w-20 rounded bg-gray-600 text-white text-center"/>
                    <Button button="=" class="mr-1 w-20 rounded bg-orange-400 text-white text-center"/>
                </div>
            </div>
        </div>
    </div>
    
</template>

<script setup>
import { ref } from 'vue';
import { mapState, mapMutations } from 'vuex'

export default {
        name: 'Calculator',
        components: {
            Button
        },
        setup() {
            const display = ref(0);
            return {
                display
            }
        },
        methods: {
            addInteger(num) {
                // Add the selected integer to the calculator input or state
                // For example:
                display = num;
                console.log(`Selected number: ${num}`);
            },
            addOperator {
                // Add the selected integer to the calculator input or state
                // For example:
                console.log(`Selected operator: ${operator}`);
            };

        },
    }

</script>

<style scoped>

</style>