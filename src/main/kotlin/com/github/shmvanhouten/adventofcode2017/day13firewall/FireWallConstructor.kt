package com.github.shmvanhouten.adventofcode2017.day13firewall

import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.RawInstructionConverter

class FireWallConstructor(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun buildFireWallFromRawInstructions(path: String): FireWall {
        val layers = rawInstructionConverter.convertRawInputIntoInstructions(path, this::buildLayer)
                .associateBy({ it.depth }, { it })
        return FireWall(layers)
    }

    private fun buildLayer(rawLayer: String): Layer {
        val (depth, range) = rawLayer.splitIntoTwo(": ")
        return Layer(depth.toInt(), range.toInt())
    }
}

private fun String.splitIntoTwo(delimeter: String): Pair<String, String> {
    val split = this.split(delimeter, limit = 2)
    return Pair(split[0], split[1])
}

