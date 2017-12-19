package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*

class AssemblyCodeDuetRunner(assemblyCode: List<AssemblyInstruction>, private val id: Int, duetAssembler: DuetAssembler) : AssemblyCodeRunner(assemblyCode) {

    private val duetPartner by lazy {
        duetAssembler.getPartnerFor(id)
    }

    private var amountOfTimesSounded = 0

    private var isWaitingToReceive = false

    private var soundQueue = listOf<Long>()

    private var index = 0

    private val registers = mutableMapOf("p" to id.toLong())


    fun runAndGetAmountOfTimesItSoundsOff(): Int {
        runUntilBothPartnersAreWaitingToReceive()
        println("program $id sounded $amountOfTimesSounded times and " +
                "program ${duetPartner.id} sounded ${duetPartner.amountOfTimesSounded} times")
        return amountOfTimesSounded
    }

    private fun runUntilBothPartnersAreWaitingToReceive(): Boolean {

        if (this.isWaitingToReceive) {
            return false
        }

        while (index < assemblyCode.size) {
            val instruction = assemblyCode[index]

            when (instruction.instructionType) {
                RECEIVE -> {
                    if (duetPartner.soundQueue.isNotEmpty()) {
                        registers.put(instruction.firstValue.toString(), duetPartner.getSound())
                    } else if (this.soundQueue.isNotEmpty() && duetPartner.isWaitingToReceive) {
                        return true
                    } else {
                        this.isWaitingToReceive = true
                        val partnerFilledTheirSoundQueue = duetPartner.runUntilBothPartnersAreWaitingToReceive()
                        if (partnerFilledTheirSoundQueue) {
                            index--
                            this.isWaitingToReceive = false
                        } else {
                            return false
                        }
                    }
                }
                SOUND -> performSoundInstruction(instruction.firstValue)
                SET -> performSetInstruction(registers, instruction)
                ADD -> performAddInstruction(registers, instruction)
                MULTIPLY -> performMultiplyInstruction(registers, instruction)
                MODULO -> performModuloInstruction(registers, instruction)
                JUMP -> index = performJumpInstruction(registers, instruction, index)
            }
            index++
        }

        return false
    }

    private fun performSoundInstruction(firstValue: Any) {
        amountOfTimesSounded++
        soundQueue += firstValue as? Long ?: registers.getOrPut(firstValue as String, { 0 })
    }

    private fun getSound(): Long {
        val sound = soundQueue.first()
        soundQueue -= sound
        return sound
    }

}