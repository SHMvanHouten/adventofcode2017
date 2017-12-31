package com.github.shmvanhouten.adventofcode2017.day13firewall

class FireWallTraverser(private val fireWall: FireWall) {

    fun getSeverity(): Int {
        var severity = 0
        var amountOfPicosecondsPassed = 0
        fireWall.forEach { step ->

            if (step is StepWithLayer) {
                val layer = step.layer
                // Every step with layer has it's scanner hit the 0 point once every x seconds where x == (range - 1) * 2
                if (amountOfPicosecondsPassed % ((layer.range - 1) * 2) == 0) {
                    severity += layer.depth * layer.range
                }
            }
            amountOfPicosecondsPassed++
        }
        return severity
    }

    fun getTimeToWaitForUndetectedTraversal(): Int? {
        return (0.until(Int.MAX_VALUE)).find { picoSecond ->
            fireWall.reset()
            passedThroughUncaught(fireWall, picoSecond)
        }
    }

    private fun passedThroughUncaught(fireWallState: FireWall, amountOfPicoSecondsWaited: Int): Boolean {
        var amountOfPicoSecondsPast = amountOfPicoSecondsWaited
        fireWallState.forEach { step ->

            if (step is StepWithLayer) {
                val layer = step.layer
                // Every step has it's scanner hit the 0 point once every x seconds where x == (range - 1) * 2
                if (amountOfPicoSecondsPast % ((layer.range - 1) * 2) == 0) {
                    return false
                }
            }
            amountOfPicoSecondsPast++
        }
        return true
    }
}