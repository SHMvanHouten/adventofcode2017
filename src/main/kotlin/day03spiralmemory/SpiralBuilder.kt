package day03spiralmemory

import day03spiralmemory.Direction.*
import day03spiralmemory.RelativePosition.*

abstract class SpiralBuilder {

    internal var directionToMoveTo = EAST
    internal var lastCoordinate = Coordinate(0, 0)

    fun buildSpiral(numberOfNodes: Int): Spiral {
        val nodes = getSpiralWithFirstNode()


        for (index in 2..numberOfNodes) {
            val nextNode = getNextNodeAndAssessDirection(nodes, index)
            nodes.put(nextNode.coordinate,  nextNode)
        }

        return Spiral(nodes)
    }

    fun getSpiralWithFirstNode(): MutableMap<Coordinate, StorageNode> {
        val firstNode = StorageNode(1, Coordinate(0, 0))
        return mutableMapOf(firstNode.coordinate to firstNode)
    }

    abstract fun getNextNodeAndAssessDirection(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode

    internal fun assessDirectionForNextNode(nodes: Map<Coordinate, StorageNode>) {
        val leftTurn = directionToMoveTo.turnLeft()
        val coordinateToTheLeft = lastCoordinate.move(leftTurn)
        if (!nodes.containsKey(coordinateToTheLeft)) {
            directionToMoveTo = leftTurn
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