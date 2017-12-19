package com.github.shmvanhouten.adventofcode2017.day18duet

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class AssemblyCodeDuetRunnerTest {

    @Test
    fun `for the test input, program 1 should have sent 3 messages before terminating`() {
        val converter = AssemblyInstructionConverter()
        val assemblyCode = converter.parseAssemblyCodeFromString("/day18/part2TestInput.txt")

        val duetAssembler = DuetAssembler(assemblyCode)
        val (_, partner1) = duetAssembler.getDuet()
        assertThat(partner1.runAndGetAmountOfTimesItSoundsOff(), equalTo(3))

    }

    @Test
    fun `it should solve the challenge input`() {
        val converter = AssemblyInstructionConverter()
        val assemblyCode = converter.parseAssemblyCodeFromString("/day18/day18.txt")

        val duetAssembler = DuetAssembler(assemblyCode)
        val (_, partner1) = duetAssembler.getDuet()
        assertThat(partner1.runAndGetAmountOfTimesItSoundsOff(), equalTo(7239))

    }


}