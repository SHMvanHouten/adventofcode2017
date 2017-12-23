package com.github.shmvanhouten.adventofcode2017.day18duet


open class AssemblyCodeRunner(val assemblyCode: List<AssemblyInstruction>) {

    fun recoverFrequency(): Long? {

        val registers = mutableMapOf<String, Long>()

        var lastFrequency = 0L
        var index = 0

        while (index < assemblyCode.size) {
            val instruction = assemblyCode[index]

            when (instruction.instructionType) {
                InstructionType.SOUND -> lastFrequency = registers.getValue(instruction.firstValue as String)
                InstructionType.SET -> performSetInstruction(registers, instruction)
                InstructionType.ADD -> performAddInstruction(registers, instruction)
                InstructionType.MULTIPLY -> performMultiplyInstruction(registers, instruction)
                InstructionType.MODULO -> performModuloInstruction(registers, instruction)
            // firstValue for recover is always a register
                InstructionType.RECEIVE -> if (registers.getValue(instruction.firstValue as String) != 0L) return lastFrequency
                InstructionType.JUMP_GREATER_THAN_ZERO -> index = performJumpInstruction(registers, instruction, index)
                else -> error("this instructionType should not be in the day 18 challenge: ${instruction.instructionType}")
            }
            index++
        }

        return null
    }

    internal fun performSetInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        val firstValue = instruction.firstValue as String
        val secondValue = instruction.secondValue
        if (secondValue is Long) {
            registers.put(firstValue, secondValue)
        } else {
            registers.put(firstValue, registers.getOrPut(secondValue as String, { 0 }))
        }
    }

    internal fun performJumpInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction, index: Int): Int {
        val checkLong = instruction.firstValue as? Long ?: registers.getOrPut(instruction.firstValue as String, {0})
        val secondValue = instruction.secondValue
        if (doesValuePassCondition(checkLong)) {
            when (secondValue) {
                is Long -> return index + secondValue.toInt() - 1
                is String -> return index + registers.getValue(secondValue).toInt() - 1
            }
        }
        return index
    }

    open fun doesValuePassCondition(checkLong: Long) = checkLong > 0L

    internal fun performAddInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first + second })
    }


    internal fun performMultiplyInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first * second })
    }

    internal fun performModuloInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first % second })
    }


    internal fun performOperation(instruction: AssemblyInstruction, registers: MutableMap<String, Long>, operation: (Long, Long) -> Long) {
        val register = instruction.firstValue as String
        val valueToPerformOperationWith = instruction.secondValue
        when (valueToPerformOperationWith) {
            is String -> registers.put(register, operation(registers.getOrPut(register, { 0L }), registers.getOrPut(valueToPerformOperationWith, { 0 })))
            is Long -> registers.put(register, operation(registers.getOrPut(register, { 0L }), valueToPerformOperationWith))
        }
    }

}