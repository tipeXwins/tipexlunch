package com.example.mainprojecte

class calc {
    val operations: Map<String, (Double, Double) -> Double> = mapOf(
        "+" to { a: Double, b: Double -> a + b },
        "-" to { a: Double, b: Double -> a - b },
        "*" to { a: Double, b: Double -> a * b },
        "/" to { a: Double, b: Double -> a / b }
    )

    fun compute(value: String): String {
        val operands = value.split("+", "-", "*", "/")

        val operator = value[operands[0].count()].toString()

        val op1 = operands[0].toDouble()
        val op2 = operands[1].toDouble()
        val function = operations[operator]

        if (function != null) {
            val result = function(op1, op2)
            return result.toString()
        }

        return ""

    }

}