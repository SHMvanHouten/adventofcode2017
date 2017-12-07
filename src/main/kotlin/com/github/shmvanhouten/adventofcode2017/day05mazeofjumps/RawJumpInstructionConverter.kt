package com.github.shmvanhouten.adventofcode2017.day05mazeofjumps

import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.Instruction
import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.RawInstructionConverter
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class RawJumpInstructionConverter: RawInstructionConverter() {

    fun getListOfJumpInstructionsFromRawInput(relativePath: String): List<JumpInstruction> {
        return convertRawInputIntoInstructions(relativePath) as List<JumpInstruction>
    }

    override fun convertInstruction(readline: String): Instruction {
        return JumpInstruction(readline.toInt())
    }
}
