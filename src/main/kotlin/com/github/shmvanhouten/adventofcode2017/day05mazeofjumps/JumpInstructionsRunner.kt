package com.github.shmvanhouten.adventofcode2017.day05mazeofjumps

abstract class JumpInstructionsRunner {

    fun runInstructions(instructionList: List<JumpInstruction>): Int {
        var index = 0
        var stepsTaken = 0
        while (index >= 0 && index < instructionList.size){
            val amountToJump = instructionList[index].amountToJump

            instructionList[index].amountToJump = calculateNewAmountToJump(amountToJump)

            index += amountToJump
            stepsTaken ++
        }
        return stepsTaken
    }

    abstract fun calculateNewAmountToJump(amountToJump :Int): Int
}