package com.github.shmvanhouten.adventofcode2017.day21fractalart

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class RuleBookBuilder(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun buildAllPossibleRulesFromRawInput(path: String): Map<String, String> {
        return rawInstructionConverter.convertRawInputIntoInstructions(path, this::parseInputPatternAndRule)
                .flatMap { extractAllInputPatterns(it) }
                .toMap()
    }

    private fun extractAllInputPatterns(inputPatternToRulePattern: Pair<String, String>): List<Pair<String, String>> {
        val (inputPattern, rulePattern) = inputPatternToRulePattern
        val setOfPossiblePatternsFromOriginal = mutableSetOf(inputPattern)
        setOfPossiblePatternsFromOriginal.add(flipPattern(inputPattern))

        var rotatedInputPattern = inputPattern
        0.until(3).forEach {
            rotatedInputPattern = rotatePattern(rotatedInputPattern)
            setOfPossiblePatternsFromOriginal.add(rotatedInputPattern)
            setOfPossiblePatternsFromOriginal.add(flipPattern(rotatedInputPattern))
        }

        return setOfPossiblePatternsFromOriginal.map{ it to rulePattern}
    }

    private fun rotatePattern(pattern: String): String {
        val split = pattern.split('/')
        val newRows = MutableList(split.size, {_ -> MutableList(split.size, {_ -> '.' }) })

        split.forEach { oldRow ->
            oldRow.forEachIndexed { index, char ->
                newRows[index] = newRows[index].plus(char)
            }
        }

        val rotatedPattern = newRows.joinToString("/")
        println("$pattern rotated to $rotatedPattern")
        return rotatedPattern

    }

    private fun flipPattern(pattern: String): String {
        return pattern.split('/').joinToString("/") { it.reversed() }
    }

    private fun parseInputPatternAndRule(readline: String): Pair<String, String> {
        val split = readline.split(" ")
        val pattern = split[0]
        val rulePattern = split[2]

        return Pair(pattern, rulePattern)
    }

}