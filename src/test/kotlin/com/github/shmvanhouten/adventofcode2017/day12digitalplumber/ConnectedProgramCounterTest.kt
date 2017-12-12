package com.github.shmvanhouten.adventofcode2017.day12digitalplumber

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ConnectedProgramCounterTest {

    @Test
    fun `it should count the amount of programs in the group of 0 to be 2`() {
        val programCounter = ConnectedProgramCounter()
        assertThat(programCounter.countAmountOfProgramsInGroup("/day12/test1.txt"), equalTo(2))
    }

    @Test
    fun `it should solve the challenge input`() {
        val programCounter = ConnectedProgramCounter()
        assertThat(programCounter.countAmountOfProgramsInGroup("/day12/day12.txt"), equalTo(239))
    }


}