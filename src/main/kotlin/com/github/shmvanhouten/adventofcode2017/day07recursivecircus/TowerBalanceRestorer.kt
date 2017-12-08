package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

class TowerBalanceRestorer {

    fun findCorrectWeightForUnbalancedProgram(towerSupportPrograms: List<TowerSupportProgram>): Int {

        val programWithUnevenlyBalancedChildren = findProgramWithUnevenlyBalancedChildren(towerSupportPrograms)

        return findWhatDivergentChildWeightShouldBe(programWithUnevenlyBalancedChildren.towersItSupports)
    }

    private fun findWhatDivergentChildWeightShouldBe(unbalancedChildren: List<TowerSupportProgram>): Int {

        val unbalancedAndBalancedSupportPrograms = unbalancedChildren
                .groupBy { childToCompare ->
                    unbalancedChildren.count { it.totalWeight == childToCompare.totalWeight } == 1
                }

        val divergentChild = unbalancedAndBalancedSupportPrograms
                .getValue(true).first()
        val currentTotalWeight = divergentChild.totalWeight

        val totalWeightToAchieve = unbalancedAndBalancedSupportPrograms
                .getValue(false)
                .first().totalWeight

        return (totalWeightToAchieve - currentTotalWeight) + divergentChild.weight
    }

    private fun findProgramWithUnevenlyBalancedChildren(towerSupportPrograms: List<TowerSupportProgram>): TowerSupportProgram {
        val programsThatSupportOthers = towerSupportPrograms
                .filter { it.namesOfTowersItSupports != null }
        return programsThatSupportOthers
                .find { parentProgram ->
                    doChildrenProgramsHaveDivergenceInTotalWeights(parentProgram.towersItSupports)
                }!!
        // we assume the input will give an unbalanced tower
    }

    private fun doChildrenProgramsHaveDivergenceInTotalWeights(towersItSupports: List<TowerSupportProgram>): Boolean {
        val programsWithDistinctTotalWeights = towersItSupports.distinctBy { it.totalWeight }
        return programsWithDistinctTotalWeights.size > 1
    }

}