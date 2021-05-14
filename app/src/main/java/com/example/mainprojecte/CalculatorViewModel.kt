package com.example.mainprojecte

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    // Dependencies
    val calculatorBrain = calc()
    // View state
    var currentInput: MutableLiveData<String> = MutableLiveData()

    init {
        currentInput.value = ""
    }


    // Task methods
    fun compute() {
        currentInput.value = calculatorBrain.compute(currentInput.value!!)
    }
    fun inputNumber(number:String) {
        currentInput.value += number
    }
    fun ac() {
        currentInput.value = ""

    }
    fun restar() {
        currentInput.value = currentInput.value!!.dropLast(1)
    }
}