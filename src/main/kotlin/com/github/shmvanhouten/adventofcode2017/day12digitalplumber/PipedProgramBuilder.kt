package com.github.shmvanhouten.adventofcode2017.day12digitalplumber

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class PipedProgramBuilder(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {


    fun buildPipedPrograms(path: String): Map<Int, PipedProgram> {
        return rawInstructionConverter
                .convertRawInputIntoInstructions(path, this::buildPipedProgram)
                .associateBy({it.id}, {it})
    }

    private fun buildPipedProgram(rawProgram: String): PipedProgram {
        val programId = rawProgram.substring(0, rawProgram.indexOf('<')).trim().toInt()
        val connectedProgramIds = rawProgram.substring(rawProgram.indexOf('>') + 2)
                .split(", ")
                .map { it.toInt() }
        return PipedProgram(programId, connectedProgramIds)
    }
}