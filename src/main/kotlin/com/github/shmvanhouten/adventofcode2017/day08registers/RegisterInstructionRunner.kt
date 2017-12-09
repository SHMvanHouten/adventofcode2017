package com.github.shmvanhouten.adventofcode2017.day08registers

import com.github.shmvanhouten.adventofcode2017.day08registers.ComparisonOperator.*

class RegisterInstructionRunner {

    private val registers = mutableMapOf<String, Int>()

    fun findLargestRegisterValueAfterRunningInstructions(listOfRegisterInstructions: List<ConditionalRegisterModificationInstruction>): Int? {

        listOfRegisterInstructions.forEach { instruction ->
            if (doesConditionPass(instruction.conditionalInstruction))
                runModificationInstruction(instruction.modificationInstruction)
        }

        return registers.values.max()
    }

    fun findHighestRegisterValueHeldAfterRunningInstructions(listOfRegisterInstructions: List<ConditionalRegisterModificationInstruction>): Int {
        var highestValue = 0
        listOfRegisterInstructions.forEach { instruction ->
            if (doesConditionPass(instruction.conditionalInstruction)) {
                runModificationInstruction(instruction.modificationInstruction)

                val registerValueAfterModification = registers.getValue(instruction.modificationInstruction.registerToModify)
                if (registerValueAfterModification > highestValue) {
                    highestValue = registerValueAfterModification
                }
            }
        }
        return highestValue
    }

    private fun doesConditionPass(conditionalInstruction: ConditionalInstruction): Boolean {

        val conditionalRegisterValue = registers.getOrPut(conditionalInstruction.registerToCheck, { 0 })

        val numberToCheckAgainst = conditionalInstruction.numberToCheckRegisterAgainst

        return when (conditionalInstruction.comparisonOperator) {
            EQUALS -> conditionalRegisterValue == numberToCheckAgainst
            DOES_NOT_EQUAL -> conditionalRegisterValue != numberToCheckAgainst
            GREATER_THAN -> conditionalRegisterValue > numberToCheckAgainst
            LESS_THAN -> conditionalRegisterValue < numberToCheckAgainst
            GREATER_THAN_OR_EQUAL_TO -> conditionalRegisterValue >= numberToCheckAgainst
            LESS_THAN_OR_EQUAL_TO -> conditionalRegisterValue <= numberToCheckAgainst
        }
    }

    private fun runModificationInstruction(modificationInstruction: ModificationInstruction) {
        val registerToModify = modificationInstruction.registerToModify
        if (!registers.containsKey(registerToModify)) {
            registers.put(registerToModify, 0)
        }

        when(modificationInstruction.modification){

            Modification.INCREASE -> {
                registers.merge(registerToModify, modificationInstruction.amountToModify,
                        {registerValue, amountToAdd -> registerValue + amountToAdd})
            }
            Modification.DECREASE -> {
                registers.merge(registerToModify, modificationInstruction.amountToModify,
                        {registerValue, amountToAdd -> registerValue - amountToAdd})
            }
        }

    }
}