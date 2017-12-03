package day03spiralmemory

class SpiralBuilderWithValuesImpl : SpiralBuilder() {

    override fun getNextNodeAndAssessDirection(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode {
        lastCoordinate = lastCoordinate.move(directionToMoveTo)

        val valueForNode: Int = getValuesOfAllAdjacentNodes(nodes, lastCoordinate)

        val node = StorageNode(index, lastCoordinate, valueForNode)

        assessDirectionForNextNode(nodes)

        return node
    }

    private fun getValuesOfAllAdjacentNodes(nodes: Map<Coordinate, StorageNode>, lastCoordinate: Coordinate): Int {
        return RelativePosition.values()
                .map { it.coordinate + lastCoordinate }
                .filter { nodes.containsKey(it) }
                .sumBy { nodes.getValue(it).value }
    }
}


