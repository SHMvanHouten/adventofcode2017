package com.github.shmvanhouten.adventofcode2017.day25turingmachine

data class State(val state: Char, private val firstInstruction: InstructionPart, private val secondInstruction: InstructionPart){

    fun executeInstruction(value: Int): Triple<Int, Int, Char> {
        return if(value == firstInstruction.conditional){
            Triple(firstInstruction.writeValue, firstInstruction.direction, firstInstruction.nextState)
        } else {
            Triple(secondInstruction.writeValue, secondInstruction.direction, secondInstruction.nextState)
        }
    }
}

data class InstructionPart(val conditional: Int, val writeValue: Int, val direction: Int, val nextState: Char )