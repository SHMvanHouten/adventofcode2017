package com.github.shmvanhouten.adventofcode2017.day04highentropypassphrases

abstract class PassPhraseChecker {

    fun countValidPhrases(phrases: String): Int {
        val listOfPhrases = phrases.split("\n")
        return listOfPhrases.count { checkIfItIsAValidPhrase(it) }
    }

    abstract fun checkIfItIsAValidPhrase(phrase: String): Boolean


}