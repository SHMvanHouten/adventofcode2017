package com.github.shmvanhouten.adventofcode2017.day21fractalart

data class RuleBook(val inputToOutputPatterns: Map<Pattern, Pattern>) {

    private val inputPatterns2X2 by lazy {
        inputToOutputPatterns.filter { it.key.size == 2 }
    }

    private val inputPatterns3X3 by lazy {
        inputToOutputPatterns.filter { it.key.size == 3 }
    }

    fun getValue(pattern: Pattern): Pattern {
        return if(pattern.size == 2) {
            inputPatterns2X2.getValue(pattern)
        } else {
            inputPatterns3X3.getValue(pattern)
        }
    }

}