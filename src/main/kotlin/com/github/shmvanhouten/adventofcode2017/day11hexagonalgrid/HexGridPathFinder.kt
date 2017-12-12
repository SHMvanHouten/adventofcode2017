package com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid

import kotlin.math.absoluteValue

class HexGridPathFinder {
    fun findShortestPath(currentCoordinate: HexCoordinate, targetCoordinate: HexCoordinate = HexCoordinate(0, 0)): Int {
        val currentX = currentCoordinate.x
        val targetX = targetCoordinate.x
        val targetY = targetCoordinate.y
        val yDifference = (currentCoordinate.y - targetY).absoluteValue
        val xDifference = (currentX - targetX).absoluteValue

        return if(xDifference/2 > yDifference){
            xDifference
        } else {
            xDifference + (yDifference - (xDifference / 2)).absoluteValue
        }
    }
}