package com.github.shmvanhouten.adventofcode2017.day18duet

class DuetAssembler(assemblyCode: List<AssemblyInstruction>) {
    private var runner0: AssemblyCodeDuetRunner = AssemblyCodeDuetRunner(assemblyCode, 0, this)
    private var runner1: AssemblyCodeDuetRunner = AssemblyCodeDuetRunner(assemblyCode, 1, this)

    fun getDuet(): Pair<AssemblyCodeDuetRunner, AssemblyCodeDuetRunner> {
        return Pair(runner0, runner1)
    }

    fun getPartnerFor(id: Long): AssemblyCodeDuetRunner {
        return when(id){
            0L -> runner1
            1L -> runner0
            else -> error("No more than 2 in a duet, $id is not one of two binary values")
        }
    }
}