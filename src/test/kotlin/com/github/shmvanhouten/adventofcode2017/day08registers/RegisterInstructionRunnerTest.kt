package com.github.shmvanhouten.adventofcode2017.day08registers

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class RegisterInstructionRunnerTest {


    @Test
    fun `it should run the increase instruction`() {
        val registerInstructionRunner = RegisterInstructionRunner()
        val instruction = ConditionalRegisterInstruction("a",
                { oldValue: Int, valueToModifyWith: Int -> oldValue + valueToModifyWith },
                5, "c",
                { int -> int == 0 })
        val actual = registerInstructionRunner.findLargestRegisterValueAfterRunningInstructions(listOf(instruction))
        assertThat(actual, equalTo(5))
    }

    @Test
    fun `it should run the decrease instruction`() {
        val registerInstructionRunner = RegisterInstructionRunner()
        val instruction = ConditionalRegisterInstruction("a",
                { oldValue: Int, valueToModifyWith: Int -> oldValue - valueToModifyWith },
                5, "a",
                { int -> int == 0 })
        val actual = registerInstructionRunner.findLargestRegisterValueAfterRunningInstructions(listOf(instruction))
        assertThat(actual, equalTo(-5))
    }


    @Test
    fun `it should run the instructions and find the largest value in any register is 1`() {
        val converter = RawConditionalRegisterInstructionConverter()
        val listOfRegisterInstructions = converter.getListOfRegisterInstructions("/day08/day08TestInput.txt")

        val registerInstructionRunner = RegisterInstructionRunner()
        assertThat(registerInstructionRunner.findLargestRegisterValueAfterRunningInstructions(listOfRegisterInstructions), equalTo(1))
    }

    @Test
    fun `it should run the instructions and find the largest register value for the challenge input`() {
        val converter = RawConditionalRegisterInstructionConverter()
        val listOfRegisterInstructions = converter.getListOfRegisterInstructions("/day08/day08.txt")

        val registerInstructionRunner = RegisterInstructionRunner()
        assertThat(registerInstructionRunner.findLargestRegisterValueAfterRunningInstructions(listOfRegisterInstructions), equalTo(1))
    }
}