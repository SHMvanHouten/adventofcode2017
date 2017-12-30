package com.github.shmvanhouten.adventofcode2017.day25turingmachine

data class State(val stateId: Char, private val firstInstruction: InstructionPart, private val secondInstruction: InstructionPart){

    fun getInstructions(value: Int): Triple<Int, CursorDirection, Char> {
        return if(value == firstInstruction.conditional){
            Triple(firstInstruction.writeValue, firstInstruction.cursorDirection, firstInstruction.nextState)
        } else {
            Triple(secondInstruction.writeValue, secondInstruction.cursorDirection, secondInstruction.nextState)
        }
    }
}

data class InstructionPart(val conditional: Int, val writeValue: Int, val cursorDirection: CursorDirection, val nextState: Char )

class TuringMachine(private val tape: MutableMap<Int, Int>, private val cursorPosition: Int, private val currentState: State) {

    fun advanceOneTick(states: Map<Char, State>): TuringMachine {
        val valueAtCursorPosition = tape.getOrPut(cursorPosition, { 0 })

        val (writeValue, direction, nextState) = currentState.getInstructions(valueAtCursorPosition)

        if (writeValue != valueAtCursorPosition) tape.put(cursorPosition, writeValue)

        val newCursorPosition = cursorPosition + direction.move

        val newState = states.getValue(nextState)

        return TuringMachine(tape, newCursorPosition, newState)
    }

    fun countNumberOfOnes(): Int {
        return tape.values.count { it == 1 }
    }
}