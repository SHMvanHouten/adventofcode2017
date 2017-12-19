package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*

class AssemblyCodeDuetRunner(assemblyCode: List<AssemblyInstruction>, private val id: Long, duetAssembler: DuetAssembler) : AssemblyCodeRunner(assemblyCode) {

    private val duetPartner by lazy {
        duetAssembler.getPartnerFor(id)
    }

    private var amountOfTimesSounded = 0L

    private var isWaitingToReceive = false

    private var soundQueue = listOf<Long>()

    private var index = 0L

    private val registers = mutableMapOf("p" to id)


    fun runAndGetAmountOfTimesItSoundsOff(): Long {
        run()
        return amountOfTimesSounded
    }

    private fun run(): Long? {

        if (this.isWaitingToReceive) {
            return null
        }

        while (index < assemblyCode.size) {
            val instruction = assemblyCode[index.toInt()]

            when (instruction.instructionType) {
            // firstValue for recover is always a register
                RECOVER -> {
                    if (duetPartner.soundQueue.isNotEmpty()) {
                        registers.put(instruction.firstValue.toString(), duetPartner.getSound())
                    } else if (this.soundQueue.isNotEmpty()) {
                        if (duetPartner.isWaitingToReceive) {
                            duetPartner.isWaitingToReceive = false
                            return this.getSound()
                        } else {
                            duetPartner.run()
                        }
                    } else {
                        isWaitingToReceive = true
                        val firstValueFromSoundQueue = duetPartner.run()
                        if (firstValueFromSoundQueue != null) {
                            registers.put(instruction.firstValue.toString(), firstValueFromSoundQueue)
                            isWaitingToReceive = false
                        } else {
                            return null
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

        return null
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