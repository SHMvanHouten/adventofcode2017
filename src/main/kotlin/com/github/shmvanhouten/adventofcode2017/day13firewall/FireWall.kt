package com.github.shmvanhouten.adventofcode2017.day13firewall

class FireWall(private val layers: Map<Int, Layer>) : Iterable<Layer> {

    override fun iterator(): Iterator<Layer> = LayerIterator()

    inner class LayerIterator : Iterator<Layer> {
        private var index = 0

        override fun hasNext(): Boolean {
            return layers.keys.any { it >= index }
        }

        override fun next(): Layer {
            index = layers.keys.first { it >= index }
            val layer = layers.getValue(index)
            index++
            return layer
        }
    }
}
