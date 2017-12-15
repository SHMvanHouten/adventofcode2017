package com.github.shmvanhouten.adventofcode2017.day15duelinggenerators

class Judge(private val generatorA: NumberGenerator, private val generatorB: NumberGenerator) {

    fun countAmountOfMatchingPairs(amountOfPairsToConsider: Int): Int {
        return (0.until(amountOfPairsToConsider)).count {
            checkForValidity(generatorA.getNext(), generatorB.getNext())
        }
    }

    private fun checkForValidity(valueA: Long, valueB: Long): Boolean {
        val last16BitsOfA: String = getLowest16BitsOfBinaryValue(valueA)
        val last16BitsOfB = getLowest16BitsOfBinaryValue(valueB)
        return last16BitsOfA == last16BitsOfB
    }

    private fun getLowest16BitsOfBinaryValue(value: Long): String {
        var binaryString = java.lang.Long.toBinaryString(value)
            while (binaryString.length < 16) {
                binaryString = '0' + binaryString
            }
            return binaryString.substring(binaryString.length - 16, binaryString.length)
        }
}