package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day22sprorificavirus.InfectionStatus.*
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class BasicVirusSimulatorTest {

    @Test
    fun `it should infect the current node turn right, and move east`() {
        val virusSimulator = BasicVirusSimulator()
        val infectedCoordinates = emptyMap<Coordinate, InfectionStatus>()
        assertThat(virusSimulator.countInfections(1, infectedCoordinates), equalTo(1))
    }

    @Test
    fun `it should infect the current node turn left, and move west, turn the next node off, turn right, and move `() {
        val virusSimulator = BasicVirusSimulator()
        val infectedCoordinates = mapOf(Coordinate(-1,0) to INFECTED)
        assertThat(virusSimulator.countInfections(2, infectedCoordinates), equalTo(1))
    }

    @Test
    fun `it should do 5 infections in 7 bursts`() {
        val virusSimulator = BasicVirusSimulator()

        val infectionMapper = InfectionMapper()
        val infectedCoordinates = infectionMapper.listAllInfectionsOnGrid("/day22/testInput.txt")

        assertThat(virusSimulator.countInfections(7, infectedCoordinates), equalTo(5))
    }

    @Test
    fun `it should do 41 infections in 70 bursts`() {
        val virusSimulator = BasicVirusSimulator()

        val infectionMapper = InfectionMapper()
        val infectedCoordinates = infectionMapper.listAllInfectionsOnGrid("/day22/testInput.txt")

        assertThat(virusSimulator.countInfections(70, infectedCoordinates), equalTo(41))
    }

    @Test
    fun `it should do 5587 infections in 10000 bursts`() {
        val virusSimulator = BasicVirusSimulator()

        val infectionMapper = InfectionMapper()
        val infectedCoordinates = infectionMapper.listAllInfectionsOnGrid("/day22/testInput.txt")

        assertThat(virusSimulator.countInfections(10000, infectedCoordinates), equalTo(5587))
    }

    @Test
    fun `it should do 5339 infections in 10000 bursts for the challenge input`() {
        val virusSimulator = BasicVirusSimulator()

        val infectionMapper = InfectionMapper()
        val infectedCoordinates = infectionMapper.listAllInfectionsOnGrid("/day22/day22.txt")

        assertThat(virusSimulator.countInfections(10000, infectedCoordinates), equalTo(5339))
    }
}