package day05mazeofjumps

class JumpInstructionsRunnerRegularImpl : JumpInstructionsRunner() {

    override fun calculateNewAmountToJump(amountToJump: Int): Int {
        return amountToJump + 1
    }
}