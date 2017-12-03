package day03spiralmemory

class SpiralBuilderNoValuesImpl : SpiralBuilder() {
    override fun getNextNodeAndAssessDirection(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode {
        lastCoordinate = lastCoordinate.move(directionToMoveTo)

        val node = StorageNode(index, lastCoordinate)

        assessDirectionForNextNode(nodes)

        return node
    }
}