package day03spiralmemory

data class Spiral(val nodes: Map<Coordinate, StorageNode>) {

    fun getNode(number: Int): StorageNode? {
        return nodes.values.find { it.number == number }
    }
}