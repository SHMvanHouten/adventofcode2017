package day03spiralmemory

class SpiralBuilderNoValuesImpl : SpiralBuilder() {
    override fun getNextNode(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode {
        assessDirection(nodes)
        lastCoordinate = lastCoordinate.move(lastDirectionMoved)

        return StorageNode(index, lastCoordinate)
    }
}