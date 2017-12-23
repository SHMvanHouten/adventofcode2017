package com.github.shmvanhouten.adventofcode2017.day23moreassembly

import com.github.shmvanhouten.adventofcode2017.day18duet.AssemblyCodeRunner
import com.github.shmvanhouten.adventofcode2017.day18duet.AssemblyInstruction
import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType

class Coprocessor(assemblyCode: List<AssemblyInstruction>) : AssemblyCodeRunner(assemblyCode) {

    fun runAssemblyProgram(): Int {

        val registers = mutableMapOf<String, Long>()

        var index = 0

        var amountOfMultiplyInvoked = 0

        while (index < assemblyCode.size) {

            val instruction = assemblyCode[index]

            when (instruction.instructionType) {
                InstructionType.SET -> performSetInstruction(registers, instruction)
                InstructionType.SUBTRACT -> performSubtractInstruction(registers, instruction)
                InstructionType.MULTIPLY -> {
                    performMultiplyInstruction(registers, instruction)
                    amountOfMultiplyInvoked++
                }
                InstructionType.JUMP_NOT_ZERO -> index = performJumpInstruction(registers, instruction, index)
                else -> error("this instructionType should not be in the day 23 challenge: ${instruction.instructionType}")
            }
            index++
        }

        return amountOfMultiplyInvoked
    }

    private fun performSubtractInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first - second })
    }

    override fun doesValuePassCondition(checkLong: Long) = checkLong != 0L
}

fun isPrime(num: Int): Boolean {
    return (2..num / 2).none {
        num % it == 0
    }
}
