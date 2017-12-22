package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Direction.NORTH
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.move
import com.github.shmvanhouten.adventofcode2017.day22sprorificavirus.NodeStatus.*

class VirusSimulator {
    fun countInfections(amountOfBursts: Int, infectedCoordinates: Set<Coordinate>): Int {
        var amountOfInfections = 0
        val infectedCoordinatesMap = infectedCoordinates.associateBy({ it }, { INFECTED }).toMutableMap()
        var currentPosition = Coordinate(0, 0)
        var currentDirection = NORTH

        0.until(amountOfBursts).forEach { _ ->
            val nodeStatus = infectedCoordinatesMap.getOrPut(currentPosition, {CLEAN})
            if (nodeStatus == INFECTED) {
                infectedCoordinatesMap[currentPosition] = CLEAN
                currentDirection = currentDirection.turnRight()
            } else {
                infectedCoordinatesMap[currentPosition] = INFECTED

                amountOfInfections++

                currentDirection = currentDirection.turnLeft()
            }
            currentPosition = currentPosition.move(currentDirection)
        }

        printGrid(infectedCoordinatesMap.filter { it.value == INFECTED }.keys)
        return amountOfInfections
    }
}

enum class NodeStatus {
    INFECTED,
    CLEAN
}

fun printGrid(infectionsOnGrid: Set<Coordinate>) {
    val minY = infectionsOnGrid.minBy { it.y }!!.y
    val minX = infectionsOnGrid.minBy { it.x }!!.x


    val maxY = infectionsOnGrid.maxBy { it.y }!!.y + 1
    val maxX = infectionsOnGrid.maxBy { it.x }!!.x + 1
    minY.until(maxY).forEach { y ->
        val row = StringBuilder()
        minX.until(maxX).forEach { x ->
            if (infectionsOnGrid.contains(Coordinate(x, y))){
                row.append('#')
            } else{
                row.append('.')
            }
        }
        println(row.toString())
    }
}