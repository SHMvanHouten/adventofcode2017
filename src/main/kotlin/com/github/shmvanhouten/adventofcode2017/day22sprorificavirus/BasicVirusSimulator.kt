package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Direction.NORTH
import com.github.shmvanhouten.adventofcode2017.day22sprorificavirus.InfectionStatus.CLEAN
import com.github.shmvanhouten.adventofcode2017.day22sprorificavirus.InfectionStatus.INFECTED

class BasicVirusSimulator: VirusSimulator {
    override fun countInfections(amountOfBursts: Int, infectedCoordinates: Map<Coordinate, InfectionStatus>): Int {

        val affectedCoordinates = infectedCoordinates.toMutableMap()

        var currentPosition = Coordinate(0, 0)
        var currentDirection = NORTH

        var amountOfInfections = 0

        0.until(amountOfBursts).forEach { _ ->
            val nodeStatus = affectedCoordinates.getOrPut(currentPosition, { CLEAN })
            if (nodeStatus == INFECTED) {

                affectedCoordinates[currentPosition] = CLEAN
                currentDirection = currentDirection.turnRight()
            } else {

                affectedCoordinates[currentPosition] = INFECTED

                amountOfInfections++

                currentDirection = currentDirection.turnLeft()
            }
            currentPosition = currentPosition.move(currentDirection)
        }

//        printGrid(affectedCoordinates.filter { it.value != CLEAN })
        return amountOfInfections
    }

}
