package com.github.shmvanhouten.adventofcode2017.day08registers

import com.github.shmvanhouten.adventofcode2017.day08registers.ComparisonOperator.*
import com.github.shmvanhouten.adventofcode2017.day08registers.Modification.DECREASE
import com.github.shmvanhouten.adventofcode2017.day08registers.Modification.INCREASE
import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.RawInstructionConverter

class RawConditionalRegisterInstructionConverter(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun getListOfRegisterInstructions(path: String): List<ConditionalRegisterModificationInstruction> {
        return rawInstructionConverter.convertRawInputIntoInstructions(path, this::convertInstruction)
    }

    private fun convertInstruction(rawInstruction: String): ConditionalRegisterModificationInstruction {
        val split = rawInstruction.split(" ")

        val modificationInstruction = buildModificationInstruction(split[0], split[1], split[2])

        val conditionalPart = buildConditionalInstruction(split[4], split[5], split[6])

        return ConditionalRegisterModificationInstruction(modificationInstruction, conditionalPart)
    }

    private fun buildModificationInstruction(registerToModify: String, operator: String, amountToModify: String): ModificationInstruction {

        val modification = when (operator) {
            "inc" -> INCREASE
            "dec" -> DECREASE
            else -> error("Warning: unknown operator input: $operator")
        }

        return ModificationInstruction(registerToModify, modification, amountToModify.toInt())
    }

    private fun buildConditionalInstruction(conditionalRegister: String, comparator: String, numberToCheckAgainst: String): ConditionalInstruction {

        val comparisonOperator = when (comparator) {
            "<" -> LESS_THAN
            ">" -> GREATER_THAN
            ">=" -> GREATER_THAN_OR_EQUAL_TO
            "<=" -> LESS_THAN_OR_EQUAL_TO
            "==" -> EQUALS
            "!=" -> DOES_NOT_EQUAL
            else -> error("Warning: unknown comparisonOperator input: $comparator")
        }

        return ConditionalInstruction(conditionalRegister, comparisonOperator, numberToCheckAgainst.toInt())
    }
}
