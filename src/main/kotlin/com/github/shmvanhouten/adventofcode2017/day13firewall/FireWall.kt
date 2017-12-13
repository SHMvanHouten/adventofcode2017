package com.github.shmvanhouten.adventofcode2017.day13firewall

data class FireWall(private val layers: Map<Int, Layer>) : Iterator<Layer> {

    private var index = 0

    override fun hasNext(): Boolean {
        return layers.keys.any { it >= index }
    }

    override fun next(): Layer {
        val layer = if (layers.containsKey(index)) {
            layers.getValue(index).copy()
        } else {
            Layer(index, range = 2, scannerPosition = 1)
        }
        index++
        passPicoSecond()
        return layer
    }


    fun reset() {
        layers.values.forEach { it.resetScanner() }
        index = 0
    }

    fun wait(picoSeconds: Int) {
        (0.until(picoSeconds)).forEach {
            passPicoSecond()
        }
    }

    private fun passPicoSecond() {
        layers.values.forEach { it.moveScanner() }
    }
}

enum class Direction {
    UP,
    DOWN;

    fun switch(): Direction {
        return if (this == UP) {
            DOWN
        } else {
            UP
        }
    }
}