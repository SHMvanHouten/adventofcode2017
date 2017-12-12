package com.github.shmvanhouten.adventofcode2017.day12digitalplumber

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class PipedProgramBuilderTest {

    @Test
    fun `it should build all the piped programs from the challenge input`() {
        val builder = PipedProgramBuilder()
        val pipedProgram = builder.buildPipedPrograms("/day12/day12.txt")
        assertThat(pipedProgram.getValue(0).id, equalTo(0))
    }
}