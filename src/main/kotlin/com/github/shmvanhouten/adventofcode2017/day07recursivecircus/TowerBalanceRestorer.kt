package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

class TowerBalanceRestorer {

    fun findCorrectWeightForUnbalancedProgram(towerSupportPrograms: List<TowerSupportProgram>): Int {

        val programWithUnevenlyBalancedChildren = findProgramWithUnevenBalance(towerSupportPrograms)

        return findWhatWeightShouldBeForTheDivergentChild(programWithUnevenlyBalancedChildren.towersItSupports)
    }

    private fun findWhatWeightShouldBeForTheDivergentChild(unbalancedChildren: List<TowerSupportProgram>): Int {
        val unbalancedAndBalacedSupportPrograms = unbalancedChildren.
                distinctBy { it.totalWeight }
                .groupBy { childToCompare -> unbalancedChildren.count { it.totalWeight == childToCompare.totalWeight } == 1 }
        val totalWeightToAchieve = unbalancedAndBalacedSupportPrograms.getValue(false).first().totalWeight
        val divergentChild = unbalancedAndBalacedSupportPrograms.getValue(true).first()
        val currentTotalWeight = divergentChild.totalWeight

        return (totalWeightToAchieve - currentTotalWeight) + divergentChild.weight

    }

    private fun findProgramWithUnevenBalance(towerSupportPrograms: List<TowerSupportProgram>): TowerSupportProgram {
        val programsThatSupportOthers = towerSupportPrograms
                .filter { it.namesOfTowersItSupports != null }
        return programsThatSupportOthers
                .find { parentProgram ->
                    findAnyDivergenceInTotalWeights(parentProgram.towersItSupports)
                }!!
        // we assume the input will give an unbalanced tower
    }

    private fun findAnyDivergenceInTotalWeights(towersItSupports: List<TowerSupportProgram>): Boolean {
        val programsWithDistinctTotalWeights = towersItSupports.distinctBy { it.totalWeight }
        return programsWithDistinctTotalWeights.size > 1
    }

}