package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class InfectionMapperTest {

    @Test
    fun `it should list the coordinates of the infections in the testInput`() {
        val infectionMapper = InfectionMapper()
        val infectionsOnGrid = infectionMapper.listAllInfectionsOnGrid("/day22/testInput.txt")

        assertThat(infectionsOnGrid.size, equalTo(2))
        assertThat(infectionsOnGrid.contains(Coordinate(-1, 0)), equalTo(true))
    }

    @Test
    fun `it should list the coordinates of the infections in the challenge Input`() {
        val infectionMapper = InfectionMapper()
        val infectionsOnGrid = infectionMapper.listAllInfectionsOnGrid("/day22/day22.txt")

        assertThat(infectionsOnGrid.contains(Coordinate(1, 1)), equalTo(true))
        assertThat(infectionsOnGrid.contains(Coordinate(0, 0)), equalTo(false))
        assertThat(infectionsOnGrid.contains(Coordinate(-12, -11)), equalTo(true))
        assertThat(infectionsOnGrid.contains(Coordinate(-12, -12)), equalTo(false))

        printGrid(infectionsOnGrid)
    }




}