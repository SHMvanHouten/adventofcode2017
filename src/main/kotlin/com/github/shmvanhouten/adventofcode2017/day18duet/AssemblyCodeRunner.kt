package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*

class AssemblyCodeRunner {
    fun recoverFrequency(assemblyCode: List<AssemblyInstruction>): Long? {

        var lastFrequency = 0L
        val registers = mutableMapOf<String, Long>()

        var index = 0L

        while (index < assemblyCode.size) {
            val instruction = assemblyCode[index.toInt()]

            val firstValue = instruction.firstValue
            val secondValue = instruction.secondValue
            when (instruction.instructionType) {
                SOUND -> {
                    lastFrequency = registers.getValue(firstValue as String)
                }
                SET -> {
                    if(secondValue is Long) {
                        registers.put(firstValue as String, secondValue)
                    } else {
                        registers.put(firstValue as String, registers.getOrPut(secondValue as String, {0}))
                    }
                }
                ADD -> performAddInstruction(registers, instruction)
                MULTIPLY -> performMultiplyInstruction(registers, instruction)
                MODULO -> performModuloInstruction(registers, instruction)
                // firstValue for recover is always a register
                RECOVER -> if (registers.getValue(firstValue as String) != 0L) return lastFrequency
                JUMP -> {
                    val checkInt = firstValue as? Long ?: registers.getValue(firstValue as String)
                    if(checkInt > 0L) {
                        when (secondValue) {
                            is Long -> index += secondValue - 1
                            is String -> index += registers.getValue(secondValue) - 1
                        }
                    }
                }
            }
            index++
        }

        return null
    }

    private fun performAddInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, {first, second -> first + second})
    }


    private fun performMultiplyInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, {first, second -> first * second})
    }

    private fun performModuloInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, {first, second -> first % second})
    }


    private fun performOperation(instruction: AssemblyInstruction, registers: MutableMap<String, Long>, operation: (Long, Long) -> Long) {
        val register = instruction.firstValue as String
        val valueToPerformOperationWith = instruction.secondValue
        when (valueToPerformOperationWith) {
            is String -> registers.put(register, operation(registers.getOrPut(register, {0L}), registers.getValue(valueToPerformOperationWith)))
            is Long -> registers.put(register, operation(registers.getOrPut(register, {0L}), valueToPerformOperationWith))
        }
    }

}