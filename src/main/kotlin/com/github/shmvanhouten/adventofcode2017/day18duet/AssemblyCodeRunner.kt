package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*

class AssemblyCodeRunner {
    fun recoverFrequency(assemblyCode: List<AssemblyInstruction>): Long? {

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

    private fun performSetInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        val firstValue = instruction.firstValue as String
        val secondValue = instruction.secondValue
        if (secondValue is Long) {
            registers.put(firstValue , secondValue)
        } else {
            registers.put(firstValue, registers.getOrPut(secondValue as String, { 0 }))
        }
    }

    private fun performJumpInstruction(registers: MutableMap<String, Long>,instruction: AssemblyInstruction, index: Long): Long {
        val checkLong = instruction.firstValue as? Long ?: registers.getValue(instruction.firstValue as String)
        val secondValue = instruction.secondValue
        if (checkLong > 0L) {
            when (secondValue) {
                is Long -> return index + secondValue - 1
                is String -> return index + registers.getValue(secondValue) - 1
            }
        }
        return index
    }

    private fun performAddInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first + second })
    }


    private fun performMultiplyInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first * second })
    }

    private fun performModuloInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first % second })
    }


    private fun performOperation(instruction: AssemblyInstruction, registers: MutableMap<String, Long>, operation: (Long, Long) -> Long) {
        val register = instruction.firstValue as String
        val valueToPerformOperationWith = instruction.secondValue
        when (valueToPerformOperationWith) {
            is String -> registers.put(register, operation(registers.getOrPut(register, { 0L }), registers.getValue(valueToPerformOperationWith)))
            is Long -> registers.put(register, operation(registers.getOrPut(register, { 0L }), valueToPerformOperationWith))
        }
    }

}