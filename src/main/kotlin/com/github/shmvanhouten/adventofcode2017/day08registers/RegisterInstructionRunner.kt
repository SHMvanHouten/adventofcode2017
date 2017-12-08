package com.github.shmvanhouten.adventofcode2017.day08registers

import com.github.shmvanhouten.adventofcode2017.day08registers.Modification.*

class RegisterInstructionRunner {
    fun findLargestRegisterValueAfterRunningInstructions(listOfRegisterInstructions: List<ConditionalRegisterInstruction>): Int? {
        val registers = mutableMapOf<String, Int>()

        listOfRegisterInstructions.forEach { instruction ->
            val conditionalRegister = instruction.conditionalRegister
            if (!registers.containsKey(conditionalRegister)) {
                registers.put(conditionalRegister, 0)
            }
            val registerToModify = instruction.registerToModify
            if(!registers.containsKey(registerToModify)){
                registers.put(registerToModify, 0)
            }
            if (instruction.registerPassesCondition(registers.getValue(conditionalRegister))) {
                registers.merge(registerToModify, instruction.amountToModify,
                        { originalAmount, newAmount -> instruction.modifyRegister(originalAmount, newAmount) })
            }
        }
        return registers.values.max()
    }

    private fun applyModificationToRegister(originalAmount: Int, newAmount: Int, modification: Modification): Int {
        return when(modification){
            INCREASE -> originalAmount + newAmount
            DECREASE -> originalAmount - newAmount
        }
    }
}