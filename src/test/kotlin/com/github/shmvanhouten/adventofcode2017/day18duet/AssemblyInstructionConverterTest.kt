package com.github.shmvanhouten.adventofcode2017.day18duet

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class AssemblyInstructionConverterTest {

    @Test
    fun `it should parse the challenge input to a list of AssemblyInstructions`() {
        val converter = AssemblyInstructionConverter()
        val assemblyCode = converter.parseAssemblyCodeFromString("/day18/day18.txt")
        assertThat(assemblyCode.size, equalTo(41))
        assemblyCode.forEach {
            println(it.instructionType)
        }
    }
}