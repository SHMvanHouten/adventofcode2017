package com.github.shmvanhouten.adventofcode2017.day18duet


data class AssemblyInstruction(val instructionType: InstructionType, val firstValue: Any, val secondValue: Any? = null)


enum class InstructionType {
    SOUND,
    SET,
    ADD,
    SUBTRACT,
    MULTIPLY,
    MODULO,
    RECEIVE,
    JUMP_GREATER_THAN_ZERO,
    JUMP_NOT_ZERO
}
//
//snd X plays a sound with a frequency equal to the value of X.
//set X Y sets register X to the value of Y.
//add X Y increases register X by the value of Y.
//mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
//mod X Y sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
//rcv X recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
//jgz X Y jumps with an offset of the value of Y, but only if the value of X is GREATER THAN zero.
// (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous instruction, and so on.)

//sub X Y decreases register X by the value of Y.
//jnz X Y jumps with an offset of the value of Y, but only if the value of X is NOT zero.
// (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous instruction, and so on.)