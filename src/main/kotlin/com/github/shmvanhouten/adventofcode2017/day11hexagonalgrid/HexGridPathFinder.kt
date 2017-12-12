package com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid

import kotlin.math.absoluteValue

class HexGridPathFinder {
    fun findShortestPath(currentCoordinate: HexCoordinate, targetCoordinate: HexCoordinate = HexCoordinate(0,0)): Int {
        if(currentCoordinate.x == targetCoordinate.x){
            return (currentCoordinate.y - targetCoordinate.y).absoluteValue
        }
        return (currentCoordinate.x - targetCoordinate.x).absoluteValue
    }
}