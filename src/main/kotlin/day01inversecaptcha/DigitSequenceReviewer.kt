package day01inversecaptcha

abstract class DigitSequenceReviewer {
    fun createSumOfRepeatedDigits(inputSequence: String): Int {

        return inputSequence
                .filterIndexed { index, digit -> digit == getDigitToMatchWith(inputSequence, index) }
                .sumBy { it.intValue() }
    }

    abstract fun getDigitToMatchWith(inputSequence: String, index: Int):Char

}

private fun Char.intValue(): Int = this.toInt() - 48
