package com.github.shmvanhouten.adventofcode2017.day25turingmachine

import com.github.shmvanhouten.adventofcode2017.day25turingmachine.CursorDirection.*

class BluePrintExecutor(private val stateParser: StateParser = StateParser()) {
    fun runInstructions(rawInput: String): Int {
        val (states, checkSum, stateToStartWith) = stateParser.parseStatesAndChecksum(rawInput)

        val turingMachine = TuringMachine(mutableMapOf(), 0, states.getValue(stateToStartWith))

        val advancedTuringMachine = 0.until(checkSum)
                .fold(turingMachine, { advancedTuringMachine: TuringMachine, _ ->
            advancedTuringMachine.advanceOneTick(states)
        })

        return advancedTuringMachine.countNumberOfOnes()
    }

    class StateParser {
        fun parseStatesAndChecksum(rawInput: String): Triple<Map<Char, State>, Int, Char> {
            val rawInstructions = rawInput.split("In state ")
            val checksum = rawInstructions[0].substring(rawInstructions[0].indexOf("after") + 6, rawInstructions[0].indexOf(" steps")).toInt()
            val stateToStartWith = rawInstructions[0][15]

            val instructions = rawInstructions.subList(1, rawInstructions.size)
                    .map { parseState(it) }
                    .associateBy({ it.stateId }, { it })
            return Triple(instructions, checksum, stateToStartWith)
        }

        private fun parseState(rawState: String): State {
            val instructionLines = rawState.split("\n")
            val state = instructionLines[0][0]
            val firstInstructionPart = parseInstructionPart(instructionLines[1], instructionLines[2], instructionLines[3], instructionLines[4])
            val secondInstructionPart = parseInstructionPart(instructionLines[5], instructionLines[6], instructionLines[7], instructionLines[8])
            return State(state, firstInstructionPart, secondInstructionPart)
        }

        private fun parseInstructionPart(conditionalLine: String, writeLine: String, moveLine: String, nextStateLine: String): InstructionPart {

            val conditional = conditionalLine.substring(conditionalLine.indexOf("is") + 3, conditionalLine.indexOf(":")).toInt()
            val write = writeLine.substring(writeLine.indexOf("value") + 6, writeLine.indexOf(".")).toInt()
            val direction = when {
                moveLine.contains("right") -> RIGHT
                moveLine.contains("left") -> LEFT
                else -> error("Unsure which way to move for instruction $moveLine")
            }
            val nextState = nextStateLine[26]
            return InstructionPart(conditional, write, direction, nextState)
        }
    }
}

enum class CursorDirection(val move: Int) {
    RIGHT(1),
    LEFT(-1)
}