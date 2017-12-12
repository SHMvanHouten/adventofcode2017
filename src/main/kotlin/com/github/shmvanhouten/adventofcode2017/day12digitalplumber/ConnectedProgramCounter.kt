package com.github.shmvanhouten.adventofcode2017.day12digitalplumber

class ConnectedProgramCounter(private val pipedProgramBuilder: PipedProgramBuilder = PipedProgramBuilder()) {



    fun countAmountOfProgramsInGroup(path: String): Int? {
        val pipedPrograms = pipedProgramBuilder.buildPipedPrograms(path)

        var unhandledPrograms = setOf(pipedPrograms.getValue(0))
        var programsInGroup = setOf<PipedProgram>()

        while (unhandledPrograms.isNotEmpty()){
            val currentProgram = unhandledPrograms.first()
            unhandledPrograms -= currentProgram
            currentProgram.connectedProgramIds.forEach { id ->
                val connectedProgram = pipedPrograms.getValue(id)
                if(!programsInGroup.contains(connectedProgram) && !unhandledPrograms.contains(connectedProgram)){
                    unhandledPrograms += connectedProgram
                }
            }

            programsInGroup += currentProgram
        }

        return programsInGroup.size
    }



}