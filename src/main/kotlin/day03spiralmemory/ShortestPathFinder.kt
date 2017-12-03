package day03spiralmemory

import kotlin.math.absoluteValue

class ShortestPathFinder(val spiralBuilder: SpiralBuilder = SpiralBuilderNoValuesImpl()) {
    fun findPathTo(nodeNumber: Int): Int {
        val spiral = spiralBuilder.buildSpiral(nodeNumber)
        val targetNodeCoordinate = spiral.getNode(nodeNumber)?.coordinate?: Coordinate(0,0)
        return targetNodeCoordinate.x.absoluteValue + targetNodeCoordinate.y.absoluteValue
    }
}