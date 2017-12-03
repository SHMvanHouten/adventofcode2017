package day03spiralmemory

class SpiralBuilderNoValuesImpl : SpiralBuilder() {
    override fun getNextNode(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode {
        lastCoordinate = lastCoordinate.move(directionToMoveTo)

        val node = StorageNode(index, lastCoordinate)

        getNewDirection(nodes)

        return node
    }
}