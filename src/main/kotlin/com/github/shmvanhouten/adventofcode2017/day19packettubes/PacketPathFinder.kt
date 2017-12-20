package com.github.shmvanhouten.adventofcode2017.day19packettubes

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Direction
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Direction.*
import com.github.shmvanhouten.adventofcode2017.day19packettubes.ComponentType.*

class PacketPathFinder(private val routingDiagram: RoutingDiagram) {

    fun getWayPointsAlongPath(): String {
        return walkPath().first
    }

    fun getAmountOfStepsTaken(): Int {
        return walkPath().second
    }

    private fun walkPath(): Pair<String, Int> {
        val wayPointsBuilder = StringBuilder()

        var currentCoordinate = routingDiagram.getStartingPoint()
        var directionPointed = SOUTH

        var stepsTaken = 0

        while (true) {
            stepsTaken++
            when (currentCoordinate.type) {
                WAY_POINT -> wayPointsBuilder.append(currentCoordinate.name)
                TURN -> directionPointed = getNewDirection(directionPointed, currentCoordinate.coordinate)
                PATH -> {}// do nothing
            }
            val possibleNextCoordinate = currentCoordinate.coordinate.getNeighbour(directionPointed)

            if (!routingDiagram.componentIsPresent(possibleNextCoordinate)) {
                return Pair(wayPointsBuilder.toString(), stepsTaken)
            } else {
                currentCoordinate = routingDiagram.getComponentAt(possibleNextCoordinate)
            }
        }
    }

    private fun getNewDirection(directionPointed: Direction, currentCoordinate: Coordinate): Direction {
        val toTheLeft = currentCoordinate.getNeighbour(directionPointed.turnLeft())
        return if (routingDiagram.componentIsPresent(toTheLeft)) {
            directionPointed.turnLeft()
        } else {
            directionPointed.turnRight()
        }
    }
}