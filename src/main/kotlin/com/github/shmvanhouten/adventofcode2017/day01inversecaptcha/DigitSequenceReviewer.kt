package com.github.shmvanhouten.adventofcode2017.day01inversecaptcha

class DigitSequenceReviewer {

    fun createSumOfRepeatedDigits(inputSequence: String): Int {
        return createSumOfRepeatedDigits(inputSequence, {sequenceLength, index -> (index + 1) % sequenceLength})
    }

    fun createSumOfDigitsRepeatedAcross(inputSequence: String): Int {
        return createSumOfRepeatedDigits(inputSequence, {sequenceLength, index -> (index + sequenceLength / 2) % sequenceLength})
    }

    private fun createSumOfRepeatedDigits(inputSequence: String, getIndexOfDigitToMatch: (Int, Int) -> Int): Int {

        return inputSequence
                .filterIndexed { index, digit -> digit == inputSequence[getIndexOfDigitToMatch(inputSequence.length, index)] }
                .sumBy { it.intValue() }
    }

}

private fun Char.intValue(): Int = this.toInt() - 48
