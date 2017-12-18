package com.github.shmvanhouten.adventofcode2017.day18duet

import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType.*
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import org.junit.Test

class AssemblyCodeRunnerTest {

    @Test
    fun `it should SET registry a to 1 SOUND it, and RECOVER it`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 1),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(1))

    }

    @Test
    fun `it should SET registry a to 1 MULTIPLY it by 7, SOUND it, and RECOVER it`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 1),
                AssemblyInstruction(MULTIPLY, "a", 7),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(7))

    }

    @Test
    fun `it should SET registry a to 1 ADD 4 to it, SOUND it, and RECOVER it`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 1),
                AssemblyInstruction(ADD, "a", 4),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(5))

    }

    @Test
    fun `it should SET registry a to 5, MODULO 3 it, SOUND it, and RECOVER it`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 5),
                AssemblyInstruction(MODULO, "a", 3),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(2))

    }

    @Test
    fun `it should SET registry a to 5, jump 2, SOUND a, and RECOVER the sound`() {
        val codeRunner = AssemblyCodeRunner()

        val assemblyCode = listOf(AssemblyInstruction(SET, "a", 5),
                AssemblyInstruction(JUMP, 4, 2),
                AssemblyInstruction(MODULO, "a", 3),
                AssemblyInstruction(SOUND, "a"),
                AssemblyInstruction(RECOVER, "a"))

        assertThat(codeRunner.recoverFrequency(assemblyCode), equalTo(5))

    }

    @Test
    fun `it should solve the test input`() {
        val converter = AssemblyInstructionConverter()
        val assemblyCode = converter.parseAssemblyCodeFromString("/day18/testInput.txt")

        val codeRunner = AssemblyCodeRunner()
        assertThat(codeRunner.recoverFrequency(assemblyCode)!!, equalTo(4))
    }

    @Test
    fun `it should solve the challenge input`() {
        val converter = AssemblyInstructionConverter()
        val assemblyCode = converter.parseAssemblyCodeFromString("/day18/day18.txt")

        val codeRunner = AssemblyCodeRunner()
        assertThat(codeRunner.recoverFrequency(assemblyCode)!!, greaterThan(862))
    }
}