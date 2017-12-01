package day01inversecaptcha

abstract class DigitSequenceReviewer {
    fun createSumOfRepeatedDigits(inputSequence: String): Int {
        var sum = 0
        for(index in 0..inputSequence.lastIndex){
            val digit = inputSequence[index]
            if(digit == getDigitToMatchWith(inputSequence, index)){
                sum += digit.intValue()
            }
        }

        return sum
    }

    abstract fun getDigitToMatchWith(inputSequence: String, index: Int):Char

}

private fun Char.intValue(): Int = this.toInt() - 48
