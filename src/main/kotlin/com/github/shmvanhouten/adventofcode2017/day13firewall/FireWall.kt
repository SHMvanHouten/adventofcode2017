package com.github.shmvanhouten.adventofcode2017.day13firewall

class FireWall(private val layers: Map<Int, Layer>) {

    private var index = 0

    fun hasNext(): Boolean {
        return layers.keys.any { it >= index }
    }

    fun next(): Layer? {
        val layer = if (layers.containsKey(index)) {
            layers.getValue(index).copy()
        } else {
            null
        }
        index++
        return layer
    }

    fun reset() {
        index = 0
    }
}
