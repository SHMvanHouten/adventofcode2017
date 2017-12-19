package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*

class AssemblyCodeDuetRunner(assemblyCode: List<AssemblyInstruction>, private val id: Long, duetAssembler: DuetAssembler) : AssemblyCodeRunner(assemblyCode) {

    private val duetPartner by lazy {
        duetAssembler.getPartnerFor(id)
    }

    var amountOfTimesSounded = 0L

    private var isWaitingToReceive = false

    private var soundQueue = listOf<Long>()

    private var index = 0L

    private val registers = mutableMapOf("p" to id)

    override fun recoverFrequency(): Long? {
        return null
    }

    fun getAmountOfTimesThisRunnerWillSound(): Long? {
        while (index < assemblyCode.size) {
            val instruction = assemblyCode[index.toInt()]

            when (instruction.instructionType) {
                SOUND -> {
                    amountOfTimesSounded++
                    if (instruction.firstValue is Long) {
                        soundQueue += instruction.firstValue
                    } else {
                        soundQueue += registers.getOrPut(instruction.firstValue as String, { 0 })
                    }
                }
                SET -> performSetInstruction(registers, instruction)
                ADD -> performAddInstruction(registers, instruction)
                MULTIPLY -> performMultiplyInstruction(registers, instruction)
                MODULO -> performModuloInstruction(registers, instruction)
            // firstValue for recover is always a register
                RECOVER -> {
                    if (duetPartner.soundQueue.isNotEmpty()) {
                        registers.put(instruction.firstValue.toString(), duetPartner.getSound())
                    } else {
                        val nextSound = duetPartner.getNextSound()
                        if (nextSound == null) {
                            return amountOfTimesSounded
                        } else {
                            registers.put(instruction.firstValue.toString(), nextSound)
                        }
                    }
                }
                JUMP -> index = performJumpInstruction(registers, instruction, index)
            }
            index++
        }

        return amountOfTimesSounded
    }

    private fun getNextSound(): Long? {

        if(this.isWaitingToReceive){
            return null
        }

        while (index < assemblyCode.size) {
            val instruction = assemblyCode[index.toInt()]

            when (instruction.instructionType) {
                SOUND -> {
                    println("${this.id} sounded ${instruction.firstValue}")
                    index++
                    amountOfTimesSounded++
                    if (instruction.firstValue is Long) {
                        return instruction.firstValue
                    }
                    return registers.getOrPut(instruction.firstValue as String, { 0 })
                }
            // firstValue for recover is always a register
                RECOVER -> {
                    if (duetPartner.soundQueue.isNotEmpty()) {
                        registers.put(instruction.firstValue.toString(), duetPartner.getSound())
                    } else {
                        isWaitingToReceive = true
                        val nextSound = duetPartner.getNextSound()
                        if (nextSound == null) {
                            index++
                            return null
                        } else {
                            registers.put(instruction.firstValue.toString(), nextSound)
                        }
                    }
                }
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

    fun getSound(): Long {
        val sound = soundQueue.first()
        soundQueue -= sound
        return sound
    }
}

enum class ProgramId {
    ZERO,
    ONE
}