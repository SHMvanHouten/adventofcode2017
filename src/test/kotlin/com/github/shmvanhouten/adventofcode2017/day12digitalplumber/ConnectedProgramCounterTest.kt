package com.github.shmvanhouten.adventofcode2017.day12digitalplumber

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ConnectedProgramCounterTest {

    @Test
    fun `it should count the amount of programs in the group of 0 to be 2`() {
        val programCounter = ConnectedProgramCounter(path = "/day12/test1.txt")
        assertThat(programCounter.countAmountOfProgramsInGroup(), equalTo(2))
    }

    @Test
    fun `it should solve the challenge input`() {
        val programCounter = ConnectedProgramCounter(path = "/day12/day12.txt")
        assertThat(programCounter.countAmountOfProgramsInGroup(), equalTo(239))
    }

    @Test
    fun `it should count a total of 2 groups in the set`() {
        val programCounter = ConnectedProgramCounter(path = "/day12/test1.txt")
        assertThat(programCounter.countTotalAmountOfGroups(), equalTo(2))
    }

    @Test
    fun `it should solve the challenge input part 2`() {
        val programCounter = ConnectedProgramCounter(path = "/day12/day12.txt")
        assertThat(programCounter.countTotalAmountOfGroups(), equalTo(215))
    }
}