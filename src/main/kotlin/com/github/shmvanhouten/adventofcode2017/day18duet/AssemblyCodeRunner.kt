package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*

class AssemblyCodeRunner {
    fun recoverFrequency(assemblyCode: List<AssemblyInstruction>): Int? {

        var lastFrequency = 0
        val registers = mutableMapOf<String, Int>()

        var index = 0

        while (index < assemblyCode.size) {
            val instruction = assemblyCode[index]

            val firstValue = instruction.firstValue
            val secondValue = instruction.secondValue
            when (instruction.instructionType) {
                SOUND -> {
                    lastFrequency = registers.getValue(firstValue as String)
                }
                SET -> {
                    if(secondValue is Int) {
                        registers.put(firstValue as String, secondValue)
                    } else {
                        registers.put(firstValue as String, registers.getOrPut(secondValue as String, {0}))
                    }
                }
                ADD -> performAddInstruction(registers, instruction)
                MULTIPLY -> performMultiplyInstruction(registers, instruction)
                MODULO -> performModuloInstruction(registers, instruction)
                // firstValue for recover is always a register
                RECOVER -> if (registers.getValue(firstValue as String) != 0) return lastFrequency
                JUMP -> {
                    val checkInt = firstValue as? Int ?: registers.getValue(firstValue as String)
                    if(checkInt != 0) {
                        when (secondValue) {
                            is Int -> index += secondValue - 1
                            is String -> index += registers.getValue(secondValue) - 1
                        }
                    }
                }
            }
            index++
        }

        return null
    }

    private fun performAddInstruction(registers: MutableMap<String, Int>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, {first, second -> first + second})
    }


    private fun performMultiplyInstruction(registers: MutableMap<String, Int>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, {first, second -> first * second})
    }

    private fun performModuloInstruction(registers: MutableMap<String, Int>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, {first, second -> first % second})
    }


    private fun performOperation(instruction: AssemblyInstruction, registers: MutableMap<String, Int>, operation: (Int, Int) -> Int) {
        val register = instruction.firstValue as String
        val valueToAdd = instruction.secondValue
        when (valueToAdd) {
            is String -> registers.put(register, operation(registers.getOrPut(register, {0}), registers.getValue(valueToAdd)))
            is Int -> registers.put(register, operation(registers.getOrPut(register, {0}), valueToAdd))
        }
    }

}