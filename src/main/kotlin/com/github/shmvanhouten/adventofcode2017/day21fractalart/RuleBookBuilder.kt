package com.github.shmvanhouten.adventofcode2017.day21fractalart

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class RuleBookBuilder(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun buildExtendedRuleBook(path: String): RuleBook {
        val inputToOutputPatterns = rawInstructionConverter.convertRawInputIntoInstructions(path, this::parseInputPatternAndRule)
                .flatMap { extractAllInputPatterns(it) }
                .toMap()
        return RuleBook(inputToOutputPatterns)
    }

    private fun extractAllInputPatterns(inputToOutputPatterns: Pair<Pattern, Pattern>): List<Pair<Pattern, Pattern>> {
        val (inputPattern, outputPattern) = inputToOutputPatterns
        val possiblePatterns = mutableSetOf(inputPattern)
        possiblePatterns.add(inputPattern.flipped())

        var rotatedInputPattern = inputPattern
        0.until(3).forEach {
            rotatedInputPattern = rotatedInputPattern.rotated()
            possiblePatterns.add(rotatedInputPattern)
            possiblePatterns.add(rotatedInputPattern.flipped())
        }

        return  possiblePatterns.map { it to outputPattern }
    }

    private fun parseInputPatternAndRule(readline: String): Pair<Pattern, Pattern> {
        val split = readline.split(" ")
        val inputPatternGrid = split[0].split('/').map { it.toList() }
        val outputPatternGrid = split[2].split('/').map { it.toList() }

        return Pair(Pattern(inputPatternGrid), Pattern(outputPatternGrid))
    }

}

fun printPattern(listOfPatterns: Pattern) {
    listOfPatterns.gridLayout.forEach {
        println(it.joinToString(""))
    }
}


