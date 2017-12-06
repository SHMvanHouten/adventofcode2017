package com.github.shmvanhouten.adventofcode2017.day01inversecaptcha

class DigitSequenceReviewerNextIndexImpl : DigitSequenceReviewer() {

    override fun getDigitToMatchWith(inputSequence: String, index: Int): Char {
        return if (index < inputSequence.lastIndex) {
            inputSequence[index + 1]
        } else {
            inputSequence.first()
        }
    }
}