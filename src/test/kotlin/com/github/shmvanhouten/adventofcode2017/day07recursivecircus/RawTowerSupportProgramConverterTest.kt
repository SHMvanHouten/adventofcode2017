package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class RawTowerSupportProgramConverterTest {

    @Test
    fun `it should convert the rawInput to a list of towerSupportPrograms`() {
        val programConverter = RawTowerSupportProgramConverter()
        val listOfTowerSupportPrograms = programConverter.getListOfTowerSupportPrograms("/src/main/resources/day07/day07.txt")
//        for (towerSupportProgram in listOfTowerSupportPrograms) {
//            println(towerSupportProgram)
//        }
        assertThat(listOfTowerSupportPrograms.size, equalTo(1454))
    }
}