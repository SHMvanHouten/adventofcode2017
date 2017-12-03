package day03spiralmemory

import day03spiralmemory.Direction.*

abstract class SpiralBuilder {

    internal var directionToMoveTo = EAST
    internal var lastCoordinate = Coordinate(0, 0)

    fun buildSpiral(numberOfNodes: Int): Spiral {
        val nodes = getSpiralWithFirstNode()


        for (index in 2..numberOfNodes) {
            val nextNode = getNextNode(nodes, index)
            nodes.put(lastCoordinate,  nextNode)
        }

        return Spiral(nodes)
    }

    fun getSpiralWithFirstNode(): MutableMap<Coordinate, StorageNode> {
        val firstNode = StorageNode(1, Coordinate(0, 0))
        val nodes = mutableMapOf(firstNode.coordinate to firstNode)
        return nodes
    }

    abstract fun getNextNode(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode

    internal fun getNewDirection(nodes: Map<Coordinate, StorageNode>) {
        val leftTurn = directionToMoveTo.turnLeft()
        val coordinateToTheLeft = lastCoordinate.move(leftTurn)
        if (!nodes.containsKey(coordinateToTheLeft)) {
            directionToMoveTo = leftTurn
        }
    }
}

internal fun Coordinate.move(direction: Direction): Coordinate {
    return when (direction) {
        NORTH -> this.north()
        EAST -> this.east()
        SOUTH -> this.south()
        WEST -> this.west()
    }
}

private fun Coordinate.west(): Coordinate {
    return Coordinate(this.x - 1, this.y)
}

private fun Coordinate.south(): Coordinate {
    return Coordinate(this.x, this.y + 1)
}

private fun Coordinate.east(): Coordinate {
    return Coordinate(this.x + 1, this.y)
}

private fun Coordinate.north(): Coordinate {
    return Coordinate(this.x, this.y - 1)
}
