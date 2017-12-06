package com.github.shmvanhouten.adventofcode2017.day05mazeofjumps

import com.natpryce.hamkrest.equalTo
import org.junit.Test

class JumpInstructionsRunnerBigOffsetDecreaserImplTest {

    @Test
    fun `it should take 10 steps to get out of the list`() {
        val runner = JumpInstructionsRunnerBigOffsetDecreaserImpl()
        val instructionList: List<JumpInstruction> = buildInstructionList(listOf(0,3,0,1,-3))
        com.natpryce.hamkrest.assertion.assertThat(runner.runInstructions(instructionList), equalTo(10))
    }

    @Test
    fun `it should solve the challenge input`() {
        val runner = JumpInstructionsRunnerBigOffsetDecreaserImpl()
        val converter = InstructionConverter()
        val instructionList = converter.convertRawInputIntoInstructions("/src/main/resources/day05/day05.txt")
        com.natpryce.hamkrest.assertion.assertThat(runner.runInstructions(instructionList), equalTo(24490906))
    }
}