package day01inversecaptcha

class DigitSequenceReviewer {
    fun createSumOfRepeatedDigits(inputSequence: String): Int {
        var sum = 0
        for(index in 0.until(inputSequence.lastIndex)){
            val digit = inputSequence[index]
            if(digit == inputSequence[index + 1]){
                sum += digit.intValue()
            }
        }
        val lastDigit = inputSequence[inputSequence.lastIndex]
        if(lastDigit == inputSequence[0]){
            sum += lastDigit.intValue()
        }

        return sum
    }
}

private fun Char.intValue(): Int = this.toInt() - 48
