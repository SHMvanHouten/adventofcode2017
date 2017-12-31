package com.github.shmvanhouten.adventofcode2017.day13firewall

class FireWallTraverser(private val fireWall: FireWall) {

    fun getSeverity(): Int =
            fireWall.layers.filter { it.willCollide() }
                    .sumBy { layer -> layer.depth * layer.range }


    fun getTimeToWaitForUndetectedTraversal(): Int? =
            (0.until(Int.MAX_VALUE)).find { picoSecond ->
                fireWall.layers.none { it.willCollide(picoSecond) }
            }
}

// Every step has its scanner hit the 0 point once every x seconds where x == (range - 1) * 2
private fun Layer.willCollide(extraPicoSeconds: Int = 0): Boolean =
        (depth + extraPicoSeconds) % ((range - 1) * 2) == 0
