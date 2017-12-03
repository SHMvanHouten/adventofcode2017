package day03spiralmemory

class SpiralBuilderWithValuesImpl : SpiralBuilder() {

    override fun getNextNode(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode {
        lastCoordinate = lastCoordinate.move(directionToMoveTo)

        val valueForNode: Int = getValuesOfAllAdjacentNodes(nodes, lastCoordinate)

        val node = StorageNode(index, lastCoordinate, valueForNode)

        getNewDirection(nodes)

        return node
    }

    private fun getValuesOfAllAdjacentNodes(nodes: Map<Coordinate, StorageNode>, lastCoordinate: Coordinate): Int {
        return RelativePosition.values()
                .map { it.coordinate + lastCoordinate }
                .filter { nodes.containsKey(it) }
                .sumBy { nodes.getValue(it).value }
    }
}

private operator fun Coordinate.plus(otherCoordinate: Coordinate): Coordinate {
    val x = this.x + otherCoordinate.x
    val y = this.y + otherCoordinate.y
    return Coordinate(x, y)
}
