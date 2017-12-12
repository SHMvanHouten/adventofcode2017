package com.github.shmvanhouten.adventofcode2017.day12digitalplumber

class ConnectedProgramCounter(private val pipedProgramBuilder: PipedProgramBuilder = PipedProgramBuilder(), private val path: String) {

    private val pipedPrograms: Map<Int, PipedProgram> = pipedProgramBuilder.buildPipedPrograms(path)

    fun countAmountOfProgramsInGroup(): Int {
        val programsInGroup = getProgramsInGroup(0)

        return programsInGroup.size
    }

    fun countTotalAmountOfGroups(): Int {
        var programsNotFoundInGroupYet = pipedPrograms.values
        var amountOfGroups = 0
        while (programsNotFoundInGroupYet.isNotEmpty()) {
            val programsInGroup = getProgramsInGroup(programsNotFoundInGroupYet.first().id)
            programsNotFoundInGroupYet -= programsInGroup
            amountOfGroups ++
        }
        return amountOfGroups
    }

    private fun getProgramsInGroup(idOfFirstProgram: Int): Set<PipedProgram> {

        var unhandledPrograms = setOf(pipedPrograms.getValue(idOfFirstProgram))
        var programsInGroup = setOf<PipedProgram>()

        while (unhandledPrograms.isNotEmpty()) {
            val currentProgram = unhandledPrograms.first()
            unhandledPrograms -= currentProgram
            currentProgram.connectedProgramIds.forEach { id ->
                val connectedProgram = pipedPrograms.getValue(id)
                if (!programsInGroup.contains(connectedProgram) && !unhandledPrograms.contains(connectedProgram)) {
                    unhandledPrograms += connectedProgram
                }
            }

            programsInGroup += currentProgram
        }
        return programsInGroup
    }

}