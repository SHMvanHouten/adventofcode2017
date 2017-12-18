package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class AssemblyCodeRunnerTest {

    @Test
    fun `it should SET registry a to 1 SOUND it, and RECOVER it`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 1L),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(1L))

    }

    @Test
    fun `it should SET registry a to 1 MULTIPLY it by 7, SOUND it, and RECOVER it`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 1L),
                AssemblyInstruction(MULTIPLY, "a", 7L),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(7L))

    }

    @Test
    fun `it should SET registry a to 1 ADD 4 to it, SOUND it, and RECOVER it`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 1L),
                AssemblyInstruction(ADD, "a", 4L),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(5L))

    }

    @Test
    fun `it should SET registry a to 5, MODULO 3 it, SOUND it, and RECOVER it`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 5L),
                AssemblyInstruction(MODULO, "a", 3L),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(2L))

    }

    @Test
    fun `it should SET registry a to 5, jump 2, SOUND a, and RECOVER the sound`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 5L),
                AssemblyInstruction(JUMP, 4L, 2L),
                AssemblyInstruction(MODULO, "a", 3L),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(5L))

    }

    @Test
    fun `it should solve the test input`() {
        val converter = AssemblyInstructionConverter()
        val assemblyCode = converter.parseAssemblyCodeFromString("/day18/testInput.txt")

        val codeRunner = AssemblyCodeRunner()
        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(4L))
    }

    @Test
    fun `it should solve the challenge input`() {
        val converter = AssemblyInstructionConverter()
        val assemblyCode = converter.parseAssemblyCodeFromString("/day18/day18.txt")

        val codeRunner = AssemblyCodeRunner()
        assertThat(codeRunner.recoverFrequency(assemblyCode)!!, equalTo(8600L))
    }
}