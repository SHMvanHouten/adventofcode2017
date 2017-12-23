package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day22sprorificavirus.InfectionStatus.CLEAN
import com.github.shmvanhouten.adventofcode2017.day22sprorificavirus.InfectionStatus.INFECTED

class BasicVirusSimulator : VirusSimulator() {


    override fun advanceBurst(affectedCoordinates: MutableMap<Coordinate, InfectionStatus>, virusState: VirusState): VirusState {
        var currentPosition = virusState.position
        var currentDirection = virusState.direction
        var amountOfInfections = virusState.amountOfInfections

        val nodeStatus = affectedCoordinates.getOrPut(currentPosition, { CLEAN })
        if (nodeStatus == INFECTED) {

            affectedCoordinates[currentPosition] = CLEAN

            currentDirection = currentDirection.turnRight()

        } else {

            affectedCoordinates[currentPosition] = INFECTED

            currentDirection = currentDirection.turnLeft()

            amountOfInfections++

        }
        currentPosition = currentPosition.move(currentDirection)
        return VirusState(currentPosition, currentDirection, amountOfInfections)

    }

}
