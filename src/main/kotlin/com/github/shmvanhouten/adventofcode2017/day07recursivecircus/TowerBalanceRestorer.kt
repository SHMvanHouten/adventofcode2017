package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

class TowerBalanceRestorer {

    fun findCorrectWeightForUnbalancedProgram(towerSupportPrograms: List<TowerSupportProgram>): Int {

        findProgramWithUnevenBalance(towerSupportPrograms)

    }

    private fun findProgramWithUnevenBalance(towerSupportPrograms: List<TowerSupportProgram>): TowerSupportProgram {
        val programsThatSupportOthers = towerSupportPrograms
                .filter { it.namesOfTowersItSupports != null }
        val supportProgramWithUnbalancedChilden = programsThatSupportOthers
                .find { parentProgram ->
                    findAnyDivergenceInTotalWeights(parentProgram.towersItSupports)
                }

    }

    private fun findAnyDivergenceInTotalWeights(towersItSupports: List<TowerSupportProgram>): Boolean {
        return towersItSupports.distinctBy { it.totalWeight }.size > 1
    }

}