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
                PATH -> {
                }// do nothing
            }
            val possibleNextCoordinate = routingDiagram
                    .getComponentAt(currentCoordinate.coordinate.getNeighbour(directionPointed))
            if (possibleNextCoordinate == null) {
                return Pair(wayPointsBuilder.toString(), stepsTaken)
            } else {
                currentCoordinate = possibleNextCoordinate
            }
        }
    }

    private fun getNewDirection(directionPointed: Direction, currentCoordinate: Coordinate): Direction {
        val component = routingDiagram.getComponentAt(currentCoordinate.getNeighbour(directionPointed.turnLeft()))
        return if(component != null){
            directionPointed.turnLeft()
        } else{
            directionPointed.turnRight()
        }
    }
}