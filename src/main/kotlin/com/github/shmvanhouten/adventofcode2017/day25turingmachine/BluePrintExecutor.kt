package com.github.shmvanhouten.adventofcode2017.day25turingmachine

class BluePrintExecutor {
    fun runInstructions(rawInput: String): Int {
        val (states, checkSum, stateToStartWith) = parseStatesAndChecksum(rawInput)

        var stateToRun = stateToStartWith
        var currentIndex = 0
        val tape = mutableMapOf(0 to 0)

        0.until(checkSum).forEach {
            val currentState = states.getValue(stateToRun)
            val valueAtCurrentIndex = tape.getOrPut(currentIndex, { 0 })
            val (writeValue, direction, nextState) = currentState.executeInstruction(valueAtCurrentIndex)
            if(writeValue != valueAtCurrentIndex){
                tape.put(currentIndex, writeValue)
            }
            currentIndex += direction

            stateToRun = nextState
        }

        return tape.values.count { it == 1 }
    }


    private fun parseStatesAndChecksum(rawInput: String): Triple<Map<Char, State>, Int, Char> {
        val rawInstructions = rawInput.split("In state ")
        val checksum = rawInstructions[0].substring(rawInstructions[0].indexOf("after") + 6, rawInstructions[0].indexOf(" steps")).toInt()
        val stateToStartWith = rawInstructions[0][15]

        val instructions = rawInstructions.subList(1, rawInstructions.size)
                .map { parseState(it) }
                .associateBy ({it.state}, {it})
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
            moveLine.contains("right") -> 1
            moveLine.contains("left") -> -1
            else -> error("Unsure which way to move for instruction $moveLine")
        }
        val nextState = nextStateLine[26]
        return InstructionPart(conditional, write, direction, nextState)
    }
}