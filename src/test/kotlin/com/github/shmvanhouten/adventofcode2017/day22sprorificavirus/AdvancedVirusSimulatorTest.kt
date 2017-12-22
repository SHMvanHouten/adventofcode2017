package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class AdvancedVirusSimulatorTest {

    @Test
    fun `it should do 26 infections in 100 bursts`() {
        val virusSimulator = AdvancedVirusSimulator()

        val infectionMapper = InfectionMapper()
        val infectedCoordinates = infectionMapper.listAllInfectionsOnGrid("/day22/testInput.txt")

        assertThat(virusSimulator.countInfections(100, infectedCoordinates), equalTo(26))
    }

    @Test
    fun `it should do 2511944 infections in 10000000 bursts`() {
        val virusSimulator = AdvancedVirusSimulator()

        val infectionMapper = InfectionMapper()
        val infectedCoordinates = infectionMapper.listAllInfectionsOnGrid("/day22/testInput.txt")

        assertThat(virusSimulator.countInfections(10000000, infectedCoordinates), equalTo(2511944))
    }

    @Test
    fun `it should do 2511944 infections in 10000000 bursts for the challenge input`() {
        val virusSimulator = AdvancedVirusSimulator()

        val infectionMapper = InfectionMapper()
        val infectedCoordinates = infectionMapper.listAllInfectionsOnGrid("/day22/day22.txt")

        assertThat(virusSimulator.countInfections(10000000, infectedCoordinates), equalTo(2512380))
    }
}