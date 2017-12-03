package day03spiralmemory

import day03spiralmemory.Direction.*

class SpiralBuilder {
    fun buildSpiral(numberOfNodes: Int): Spiral {
        val firstNode = StorageNode(1, Coordinate(0, 0))
        val nodes = mutableMapOf(firstNode.coordinate to firstNode)

        var directionToMove = EAST
        var currentCoordinate = Coordinate(1, 0)

        for (index in 2..numberOfNodes) {
            nodes.put(currentCoordinate, StorageNode(index, currentCoordinate))

            val leftTurn = directionToMove.turnLeft()
            val coordinateToTheLeft = currentCoordinate.move(leftTurn)
            if (!nodes.containsKey(coordinateToTheLeft)) {
                directionToMove = leftTurn
                currentCoordinate = coordinateToTheLeft
            }else {
                currentCoordinate = currentCoordinate.move(directionToMove)
            }

        }
        return Spiral(nodes)
    }


}

private fun Coordinate.move(direction: Direction): Coordinate {
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
