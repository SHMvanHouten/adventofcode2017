package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class TowerBalanceRestorerTest {

    @Test
    fun `it should find the unbalanced tower and get the weight the bad program should be`() {
        val programConverter = RawTowerSupportProgramConverter()
        val listOfTowerSupportPrograms = programConverter.getListOfTowerSupportPrograms("/day07/testInput.txt")

        val balanceRestorer = TowerBalanceRestorer()
        assertThat(balanceRestorer.findCorrectWeightForUnbalancedProgram(listOfTowerSupportPrograms), equalTo(60))
    }
}