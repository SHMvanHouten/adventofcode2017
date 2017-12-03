package day03spiralmemory

class WrittenValueFinder(private val spiralBuilder: SpiralBuilder = SpiralBuilderWithValuesImpl()) {

    fun findFirstWrittenValueLargerThan(valueToCompare: Int): Int {
        val spiral = spiralBuilder.getSpiralWithFirstNode()

        var currentSpiralValue = 1
        var index = 2
        while (currentSpiralValue <= valueToCompare){
            val nextNode = spiralBuilder.getNextNodeAndAssessDirection(spiral, index)

            currentSpiralValue = nextNode.value

            spiral.put(nextNode.coordinate, nextNode)

            index++
        }
        return currentSpiralValue
    }
}

