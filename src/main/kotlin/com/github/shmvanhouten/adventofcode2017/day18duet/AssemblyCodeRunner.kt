package com.github.shmvanhouten.adventofcode2017.day18duet


abstract class AssemblyCodeRunner(val assemblyCode: List<AssemblyInstruction>) {
    abstract fun recoverFrequency(): Long?

    internal fun performSetInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        val firstValue = instruction.firstValue as String
        val secondValue = instruction.secondValue
        if (secondValue is Long) {
            registers.put(firstValue , secondValue)
        } else {
            registers.put(firstValue, registers.getOrPut(secondValue as String, { 0 }))
        }
    }

    internal fun performJumpInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction, index: Long): Long {
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

    internal fun performAddInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first + second })
    }


    internal fun performMultiplyInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first * second })
    }

    internal fun performModuloInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first % second })
    }


    private fun performOperation(instruction: AssemblyInstruction, registers: MutableMap<String, Long>, operation: (Long, Long) -> Long) {
        val register = instruction.firstValue as String
        val valueToPerformOperationWith = instruction.secondValue
        when (valueToPerformOperationWith) {
            is String -> registers.put(register, operation(registers.getOrPut(register, { 0L }), registers.getOrPut(valueToPerformOperationWith, {0})))
            is Long -> registers.put(register, operation(registers.getOrPut(register, { 0L }), valueToPerformOperationWith))
        }
    }

}