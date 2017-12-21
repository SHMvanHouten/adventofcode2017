package com.github.shmvanhouten.adventofcode2017.day21fractalart

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class RuleBookBuilder(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun buildAllPossibleRulesFromRawInput(path: String): Map<Pattern, Iterable<Pattern>> {
        return rawInstructionConverter.convertRawInputIntoInstructions(path, this::parseInputPatternAndRule)
                .map { extractAllInputPatterns(it) }
                .toMap()
    }

    private fun extractAllInputPatterns(inputPatternToRulePattern: Pair<Pattern, Pattern>): Pair<Pattern, Iterable<Pattern>> {
        val (inputPattern, rulePattern) = inputPatternToRulePattern
        val setOfPossiblePatternsFromOriginal = mutableSetOf(inputPattern)
        setOfPossiblePatternsFromOriginal.add(inputPattern.flipped())

        var rotatedInputPattern = inputPattern
        0.until(3).forEach {
            rotatedInputPattern = rotatedInputPattern.rotated()
            setOfPossiblePatternsFromOriginal.add(rotatedInputPattern)
            setOfPossiblePatternsFromOriginal.add(rotatedInputPattern.flipped())
        }

        return  rulePattern to setOfPossiblePatternsFromOriginal.asIterable()
    }

    private fun parseInputPatternAndRule(readline: String): Pair<Pattern, Pattern> {
        val split = readline.split(" ")
        val pattern = split[0].split('/').map { it.toList() }
        val rulePattern = split[2].split('/').map { it.toList() }

        return Pair(Pattern(pattern), Pattern(rulePattern))
    }

}

fun printPattern(listOfPatterns: Pattern) {
    listOfPatterns.gridLayout.forEach {
        println(it.joinToString(""))
    }
}


