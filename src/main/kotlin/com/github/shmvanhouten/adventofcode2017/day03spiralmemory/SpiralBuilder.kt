package com.github.shmvanhouten.adventofcode2017.day03spiralmemory

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Direction.*
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.RelativePosition.*

abstract class SpiralBuilder {

    internal var lastDirectionMoved = SOUTH
    internal var lastCoordinate = Coordinate(0, 0)

    fun buildSpiral(numberOfNodes: Int): Spiral {
        val nodes = getSpiralWithFirstNode()


        for (index in 2..numberOfNodes) {
            val nextNode = getNextNode(nodes, index)
            nodes.put(nextNode.coordinate,  nextNode)
        }

        return Spiral(nodes)
    }

    fun getSpiralWithFirstNode(): MutableMap<Coordinate, StorageNode> {
        val firstNode = StorageNode(1, Coordinate(0, 0))
        return mutableMapOf(firstNode.coordinate to firstNode)
    }

    abstract fun getNextNode(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode

    internal fun assessDirection(nodes: Map<Coordinate, StorageNode>) {
        val leftTurn = lastDirectionMoved.turnLeft()
        val coordinateToTheLeft = lastCoordinate.move(leftTurn)
        if (!nodes.containsKey(coordinateToTheLeft)) {
            lastDirectionMoved = leftTurn
        }
    }
}

internal fun Coordinate.move(direction: Direction): Coordinate {
    return when (direction) {
        NORTH -> this + TOP.coordinate
        EAST -> this + RIGHT.coordinate
        SOUTH -> this + BOTTOM.coordinate
        WEST -> this + LEFT.coordinate
    }
}


internal operator fun Coordinate.plus(otherCoordinate: Coordinate): Coordinate {
    val x = this.x + otherCoordinate.x
    val y = this.y + otherCoordinate.y
    return Coordinate(x, y)
}