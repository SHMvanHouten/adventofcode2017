package com.github.shmvanhouten.adventofcode2017.day03spiralmemory

class SpiralBuilderWithValuesImpl : SpiralBuilder() {

    override fun getNextNode(nodes: Map<Coordinate, StorageNode>, index: Int): StorageNode {
        assessDirection(nodes)
        lastCoordinate = lastCoordinate.move(lastDirectionMoved)

        val valueForNode: Int = getValuesOfAllAdjacentNodes(nodes, lastCoordinate)

        return StorageNode(index, lastCoordinate, valueForNode)
    }

    private fun getValuesOfAllAdjacentNodes(nodes: Map<Coordinate, StorageNode>, lastCoordinate: Coordinate): Int {
        return RelativePosition.values()
                .map { it.coordinate + lastCoordinate }
                .filter { nodes.containsKey(it) }
                .sumBy { nodes.getValue(it).value }
    }
}


