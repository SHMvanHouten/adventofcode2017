package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Direction
import com.github.shmvanhouten.adventofcode2017.day22sprorificavirus.InfectionStatus.*

class AdvancedVirusSimulator : VirusSimulator() {

    override fun advanceBurst(affectedCoordinates: MutableMap<Coordinate, InfectionStatus>, virusState: VirusState): VirusState {
        var currentPosition = virusState.position
        var currentDirection = virusState.direction
        var amountOfInfections = virusState.amountOfInfections

        val nodeStatus = affectedCoordinates.getOrPut(currentPosition, { CLEAN })

        affectedCoordinates[currentPosition] = affectedCoordinates.getValue(currentPosition).advanceInfectionStatus()

        when (nodeStatus) {

            CLEAN -> currentDirection = currentDirection.turnLeft()
            WEAKENED -> amountOfInfections++
            INFECTED -> currentDirection = currentDirection.turnRight()
            FLAGGED -> currentDirection = currentDirection.turnBack()
        }

        currentPosition = currentPosition.move(currentDirection)

        return VirusState(currentPosition, currentDirection, amountOfInfections)

    }
}