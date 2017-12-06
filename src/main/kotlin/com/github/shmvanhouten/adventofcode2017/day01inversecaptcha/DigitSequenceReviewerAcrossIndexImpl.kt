package com.github.shmvanhouten.adventofcode2017.day01inversecaptcha

class DigitSequenceReviewerAcrossIndexImpl : DigitSequenceReviewer() {

    override fun getDigitToMatchWith(inputSequence: String, index: Int): Char {
        val sequenceLength = inputSequence.length
        return inputSequence[(index + sequenceLength / 2) % sequenceLength]
    }
}