package com.github.shmvanhouten.adventofcode2017.day05mazeofjumps

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class JumpInstructionsRunnerTest {

    @Test
    fun `it should take 5 steps to get out of the list`() {
        val runner = JumpInstructionsRunnerRegularImpl()
        val instructionList: List<JumpInstruction> = buildInstructionList(listOf(0,3,0,1,-3))
        assertThat(runner.runInstructions(instructionList), equalTo(5))
    }

    @Test
    fun `it should solve the challenge input`() {
        val runner = JumpInstructionsRunnerRegularImpl()
        val converter = RawJumpInstructionConverter()
        val instructionList = converter.getListOfJumpInstructionsFromRawInput("/day05/day05.txt")
        assertThat(runner.runInstructions(instructionList), equalTo(325922))
    }

}

fun buildInstructionList(rawInstructions: List<Int>): List<JumpInstruction> {
    return rawInstructions.map { JumpInstruction(it) }
}
