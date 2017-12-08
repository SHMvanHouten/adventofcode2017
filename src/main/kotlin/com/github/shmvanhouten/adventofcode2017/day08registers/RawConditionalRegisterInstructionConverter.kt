package com.github.shmvanhouten.adventofcode2017.day08registers

import com.github.shmvanhouten.adventofcode2017.day08registers.Modification.*
import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.RawInstructionConverter

class RawConditionalRegisterInstructionConverter(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun getListOfRegisterInstructions(path: String): List<ConditionalRegisterInstruction> {
        return rawInstructionConverter.convertRawInputIntoInstructions(path, this::convertInstruction)
    }

    private fun convertInstruction(readline: String): ConditionalRegisterInstruction {
        val split = readline.split(" ")
        val registerToModify = split[0]
        val modification = when (split[1]) {
            "inc" -> { oldValue: Int, valueToAdd: Int -> oldValue + valueToAdd}
            else -> { oldValue: Int, valueToSubtract: Int ->  oldValue - valueToSubtract }
        }
        val amountToModify = split[2].toInt()
        val conditionalRegister = split[4]
        val operation = buildOperationMethod(split[5], split[6].toInt())
        return ConditionalRegisterInstruction(registerToModify, modification, amountToModify, conditionalRegister, operation)

    }

    private fun buildOperationMethod(comparator: String, numberToCheckAgainst: Int): (Int) -> Boolean {
        return when (comparator) {
            "<" -> { registerValue: Int -> registerValue < numberToCheckAgainst }
            ">" -> { registerValue: Int -> registerValue > numberToCheckAgainst }
            ">=" -> { registerValue: Int -> registerValue >= numberToCheckAgainst }
            "<=" -> { registerValue: Int -> registerValue <= numberToCheckAgainst }
            "==" -> { registerValue: Int -> registerValue == numberToCheckAgainst }
            else -> { registerValue: Int -> registerValue != numberToCheckAgainst }
        }
    }
}

data class ConditionalRegisterInstruction(val registerToModify: String,
                                          val modifyRegister: (Int, Int) -> Int,
                                          val amountToModify: Int,
                                          val conditionalRegister: String,
                                          val registerPassesCondition: (Int) -> Boolean)

enum class Modification {
    INCREASE,
    DECREASE
}