package com.github.shmvanhouten.adventofcode2017.day03spiralmemory

class WrittenValueFinder(private val spiralBuilder: SpiralBuilder = SpiralBuilderWithValuesImpl()) {

    fun findFirstWrittenValueLargerThan(valueToCompare: Int): Int {
        val spiral = spiralBuilder.getSpiralWithFirstNode()

        var currentSpiralValue = 1
        var index = 2
        while (currentSpiralValue <= valueToCompare){
            val nextNode = spiralBuilder.getNextNode(spiral, index)

            currentSpiralValue = nextNode.value

            spiral.put(nextNode.coordinate, nextNode)

            index++
        }
        return currentSpiralValue
    }
}

