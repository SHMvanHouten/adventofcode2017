package com.github.shmvanhouten.adventofcode2017.day13firewall

class FireWall(private val layers: Map<Int, Step>): Iterable<Step> {

    private var index = 0

    fun reset() {
        index = 0
    }

    override fun iterator(): Iterator<Step> = LayerIterator()

    inner class LayerIterator: Iterator<Step>{
        override fun hasNext(): Boolean {
            return layers.keys.any { it >= index }
        }

        override fun next(): Step {
            val layer = if (layers.containsKey(index)) {
                layers.getValue(index)
            } else {
                emptyStep()
            }
            index++
            return layer
        }
    }
}
