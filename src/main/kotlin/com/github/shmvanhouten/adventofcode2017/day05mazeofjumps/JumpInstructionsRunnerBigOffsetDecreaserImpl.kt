package com.github.shmvanhouten.adventofcode2017.day05mazeofjumps

class JumpInstructionsRunnerBigOffsetDecreaserImpl : JumpInstructionsRunner() {

    override fun calculateNewAmountToJump(amountToJump: Int): Int {
        return if(amountToJump >= 3){
            amountToJump - 1
        }else{
            return amountToJump + 1
        }
    }
}