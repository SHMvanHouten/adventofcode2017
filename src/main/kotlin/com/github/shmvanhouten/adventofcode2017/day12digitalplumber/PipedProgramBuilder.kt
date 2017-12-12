package com.github.shmvanhouten.adventofcode2017.day12digitalplumber

import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.RawInstructionConverter

class PipedProgramBuilder(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    var pipedProgramList = emptySet<PipedProgram>()

    fun buildPipedPrograms(path: String): PipedProgram {
        pipedProgramList = rawInstructionConverter.convertRawInputIntoInstructions(path, this::buildPipedProgram).toSet()
        return pipedProgramList.find { it.id == 0 }!!
    }

    private fun buildPipedProgram(rawProgram: String): PipedProgram {
        val programId = rawProgram.substring(0, rawProgram.indexOf('<')).trim().toInt()
        val connectedProgramIds = rawProgram.substring(rawProgram.indexOf('>') + 2)
                .split(", ")
                .map { it.toInt() }
        return PipedProgram(programId, connectedProgramIds, this)
    }

    fun getPipedProgram(programId: Int): PipedProgram {
        return pipedProgramList.find { it.id == programId }!!
    }
}