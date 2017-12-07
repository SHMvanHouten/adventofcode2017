package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class BottomProgramFinderTest {

    @Test
    fun `it should find the bottom program for the test input`() {
        val programConverter = RawTowerSupportProgramConverter()
        val listOfTowerSupportPrograms = programConverter.getListOfTowerSupportPrograms("/src/main/resources/day07/testInput.txt")


        val programFinder = BottomProgramFinder()
        assertThat(programFinder.findBottomProgramName(listOfTowerSupportPrograms), equalTo("tknk"))
    }

    @Test
    fun `it should solve the challenge input`() {
        val programConverter = RawTowerSupportProgramConverter()
        val listOfTowerSupportPrograms = programConverter.getListOfTowerSupportPrograms("/src/main/resources/day07/day07.txt")


        val programFinder = BottomProgramFinder()
        assertThat(programFinder.findBottomProgramName(listOfTowerSupportPrograms), equalTo("mkxke"))
    }
}