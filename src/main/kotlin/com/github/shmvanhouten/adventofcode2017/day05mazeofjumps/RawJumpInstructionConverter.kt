package com.github.shmvanhouten.adventofcode2017.day05mazeofjumps

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class RawJumpInstructionConverter(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun getListOfJumpInstructionsFromRawInput(relativePath: String): List<JumpInstruction> {
        return rawInstructionConverter.convertRawInputIntoInstructions(relativePath, this::convertInstruction)
    }

    private fun convertInstruction(readline: String): JumpInstruction {
        return JumpInstruction(readline.toInt())
    }
}
