package com.github.shmvanhouten.adventofcode2017.day13firewall

class FireWallTraverser(private val fireWall: FireWall) {

    fun getSeverity(): Int {

        // Every step has its scanner hit the 0 point once every x seconds where x == (range - 1) * 2
        return fireWall
                .filter { layer -> layer.depth % ((layer.range - 1) * 2) == 0 }
                .sumBy { layer ->  layer.depth * layer.range }

    }

    fun getTimeToWaitForUndetectedTraversal(): Int? {
        return (0.until(Int.MAX_VALUE)).find { picoSecond ->
            fireWall.reset()
            passedThroughUncaught(fireWall, picoSecond)
        }
    }

    private fun passedThroughUncaught(fireWallState: FireWall, amountOfPicoSecondsWaited: Int): Boolean {

        // Every step has its scanner hit the 0 point once every x seconds where x == (range - 1) * 2
        return fireWallState.none { layer -> (layer.depth + amountOfPicoSecondsWaited) % ((layer.range - 1) * 2) == 0 }
    }
}