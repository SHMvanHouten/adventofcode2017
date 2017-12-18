package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*
import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class AssemblyInstructionConverter(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun parseAssemblyCodeFromString(path: String): List<AssemblyInstruction> {
        return rawInstructionConverter.convertRawInputIntoInstructions(path, this::parseLineToAssemblyInstruction)
    }

    private fun parseLineToAssemblyInstruction(readline: String): AssemblyInstruction {
        val readlineParts = readline.split(" ")

        val instructionType = getInstructionType(readlineParts, readline)

        if (instructionType == SOUND || instructionType == RECOVER) return AssemblyInstruction(instructionType, readlineParts[1])

        val possibleFirstValue = readlineParts[1].toLongOrNull()
        val possibleSecondValue = readlineParts[2].toLongOrNull()
        if(possibleFirstValue == null){
            if(possibleSecondValue == null){
                return AssemblyInstruction(instructionType, readlineParts[1], readlineParts[2])
            }
            return AssemblyInstruction(instructionType, readlineParts[1], possibleSecondValue)
        } else {
            return AssemblyInstruction(instructionType, possibleFirstValue, possibleSecondValue!!)
        }
    }

    private fun getInstructionType(readlineParts: List<String>, readline: String): InstructionType {
        return when (readlineParts[0]) {
            "snd" -> SOUND
            "set" -> SET
            "add" -> ADD
            "mul" -> MULTIPLY
            "mod" -> MODULO
            "rcv" -> RECOVER
            "jgz" -> JUMP
            else -> error("unknown instructionType for $readline")
        }
    }
}