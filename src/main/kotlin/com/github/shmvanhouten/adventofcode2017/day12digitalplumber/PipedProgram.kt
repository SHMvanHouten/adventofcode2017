package com.github.shmvanhouten.adventofcode2017.day12digitalplumber

data class PipedProgram(val id: Int,
                        private val connectedProgramIds: List<Int>,
                        private val pipedProgramBuilder: PipedProgramBuilder){

    val connectedPrograms by lazy {
        connectedProgramIds.map { pipedProgramBuilder.getPipedProgram(it) }
    }
}