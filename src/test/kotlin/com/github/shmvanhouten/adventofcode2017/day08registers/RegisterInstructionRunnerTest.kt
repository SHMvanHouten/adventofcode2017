package com.github.shmvanhouten.adventofcode2017.day08registers

import com.github.shmvanhouten.adventofcode2017.day08registers.ComparisonOperator.EQUALS
import com.github.shmvanhouten.adventofcode2017.day08registers.ComparisonOperator.GREATER_THAN_OR_EQUAL_TO
import com.github.shmvanhouten.adventofcode2017.day08registers.Modification.DECREASE
import com.github.shmvanhouten.adventofcode2017.day08registers.Modification.INCREASE
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class RegisterInstructionRunnerTest {


    @Test
    fun `it should run the increase instruction`() {
        val registerInstructionRunner = RegisterInstructionRunner()
        val instruction = ConditionalRegisterModificationInstruction(
                ModificationInstruction("a", INCREASE, 5),
                ConditionalInstruction("c", EQUALS, 0))
        val actual = registerInstructionRunner.findLargestRegisterValueAfterRunningInstructions(listOf(instruction))
        assertThat(actual, equalTo(5))
    }

    @Test
    fun `it should run the decrease instruction`() {
        val registerInstructionRunner = RegisterInstructionRunner()
        val instruction = ConditionalRegisterModificationInstruction(ModificationInstruction("a", DECREASE, 5),
                ConditionalInstruction("a", GREATER_THAN_OR_EQUAL_TO, 0))
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
        assertThat(registerInstructionRunner.findLargestRegisterValueAfterRunningInstructions(listOfRegisterInstructions), equalTo(8022))
    }


    @Test
    fun `it should run the instructions and find the highest held value in any register is 10`() {
        val converter = RawConditionalRegisterInstructionConverter()
        val listOfRegisterInstructions = converter.getListOfRegisterInstructions("/day08/day08TestInput.txt")

        val registerInstructionRunner = RegisterInstructionRunner()
        assertThat(registerInstructionRunner.findHighestRegisterValueHeldAfterRunningInstructions(listOfRegisterInstructions), equalTo(10))
    }


    @Test
    fun `it should run the instructions and find the highest held register value for the challenge input`() {
        val converter = RawConditionalRegisterInstructionConverter()
        val listOfRegisterInstructions = converter.getListOfRegisterInstructions("/day08/day08.txt")

        val registerInstructionRunner = RegisterInstructionRunner()
        assertThat(registerInstructionRunner.findHighestRegisterValueHeldAfterRunningInstructions(listOfRegisterInstructions), equalTo(8022))
    }
}