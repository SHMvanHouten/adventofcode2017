package com.github.shmvanhouten.adventofcode2017.day02corruptionchecksum

class ChecksumCalculatorDivisibleValuesImpl : ChecksumCalculator() {

    override fun calculateRowChecksum(rawInput: String): Int {
        val rowValues = getSortedRowValues(rawInput)
        for (highRowValue in rowValues) {
            var index = rowValues.lastIndex
            while (highRowValue / 2 >= rowValues[index]){
                val lowRowValue = rowValues[index]
                if(highRowValue % lowRowValue == 0){
                    return highRowValue / lowRowValue
                }
                index --
            }
        }
        println("bad input: no divisible values found for $rowValues")
        return Int.MIN_VALUE
    }
}