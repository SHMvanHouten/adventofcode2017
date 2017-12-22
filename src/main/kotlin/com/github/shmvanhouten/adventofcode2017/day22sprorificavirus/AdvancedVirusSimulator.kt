package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Direction
import com.github.shmvanhouten.adventofcode2017.day22sprorificavirus.InfectionStatus.*

class AdvancedVirusSimulator : VirusSimulator {

    override fun countInfections(amountOfBursts: Int, infectedCoordinates: Map<Coordinate, InfectionStatus>): Int {

        val affectedCoordinates = infectedCoordinates.toMutableMap()
        var currentPosition = Coordinate(0, 0)
        var currentDirection = Direction.NORTH
        var amountOfInfections = 0

        0.until(amountOfBursts).forEach { _ ->
            val nodeStatus = affectedCoordinates.getOrPut(currentPosition, { CLEAN })

            affectedCoordinates[currentPosition] = affectedCoordinates.getValue(currentPosition).advanceInfectionStatus()

            when (nodeStatus) {

                CLEAN -> currentDirection = currentDirection.turnLeft()
                WEAKENED -> amountOfInfections++
                INFECTED -> currentDirection = currentDirection.turnRight()
                FLAGGED -> currentDirection = currentDirection.turnBack()
            }

            currentPosition = currentPosition.move(currentDirection)
        }

//        printGrid(affectedCoordinates.filter { it.value != CLEAN })
        return amountOfInfections
    }

}