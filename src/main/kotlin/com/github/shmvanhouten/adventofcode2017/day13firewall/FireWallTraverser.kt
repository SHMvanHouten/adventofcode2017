package com.github.shmvanhouten.adventofcode2017.day13firewall

class FireWallTraverser(private val fireWall: FireWall) {

    fun getSeverity(): Int {
        var severity = 0
        while (fireWall.hasNext()) {
            val layer = fireWall.next()

            if(layer.scannerPosition == 0){
                severity += layer.depth * layer.range
            }
        }
        println(severity)
        return severity
    }

    fun getTimeToWaitForUndetectedTraversal(): Int? {
        return (0.until(Int.MAX_VALUE)).find {
            fireWall.wait(1)
            passedThroughUncaught(fireWall.copy())
        }
    }

    private fun passedThroughUncaught(fireWallState: FireWall): Boolean {
        while (fireWallState.hasNext()) {
            val layer = fireWallState.next()

            if(layer.scannerPosition == 0){
                return false
            }
        }
        return true
    }
}