package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*

class AssemblyCodeSoloRunner : AssemblyCodeRunner() {

    override fun recoverFrequency(assemblyCode: List<AssemblyInstruction>): Long? {

        val registers = mutableMapOf<String, Long>()

        var lastFrequency = 0L
        var index = 0L

        while (index < assemblyCode.size) {
            val instruction = assemblyCode[index.toInt()]

            when (instruction.instructionType) {
                SOUND -> lastFrequency = registers.getValue(instruction.firstValue as String)
                SET -> performSetInstruction(registers, instruction)
                ADD -> performAddInstruction(registers, instruction)
                MULTIPLY -> performMultiplyInstruction(registers, instruction)
                MODULO -> performModuloInstruction(registers, instruction)
            // firstValue for recover is always a register
                RECOVER -> if (registers.getValue(instruction.firstValue as String) != 0L) return lastFrequency
                JUMP -> index = performJumpInstruction(registers, instruction, index)
            }
            index++
        }

        return null
    }
}