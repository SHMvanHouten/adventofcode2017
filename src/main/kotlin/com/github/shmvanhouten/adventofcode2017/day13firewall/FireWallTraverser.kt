package com.github.shmvanhouten.adventofcode2017.day13firewall

class FireWallTraverser(private val fireWall: FireWall) {

    fun getSeverity(): Int =
            fireWall.filter { layer -> willCollide(layer) }
                    .sumBy { layer -> layer.depth * layer.range }


    fun getTimeToWaitForUndetectedTraversal(): Int? =
            (0.until(Int.MAX_VALUE)).find { picoSecond ->
                fireWall.none { layer -> willCollide(layer, picoSecond) }
            }


    // Every step has its scanner hit the 0 point once every x seconds where x == (range - 1) * 2
    private fun willCollide(layer: Layer, picoSecondsWaited: Int = 0) =
            (layer.depth + picoSecondsWaited) % ((layer.range - 1) * 2) == 0
}