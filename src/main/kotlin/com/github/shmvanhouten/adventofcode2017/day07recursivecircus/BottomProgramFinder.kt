package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

class BottomProgramFinder {
    fun findBottomProgramName(listOfTowerSupportPrograms: List<TowerSupportProgram>): String? {
        val programsThatSupportATower = listOfTowerSupportPrograms
                .filter { it.namesOfTowersItSupports != null }
        val bottomProgram = programsThatSupportATower
                .find {
                    program -> programsThatSupportATower
                        .none { it.namesOfTowersItSupports!!.any { it == program.name } }
                }
        return bottomProgram?.name
    }
}