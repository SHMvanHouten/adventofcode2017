package com.github.shmvanhouten.adventofcode2017.day13firewall

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter
import com.github.shmvanhouten.adventofcode2017.util.splitIntoTwo

class FireWallConstructor(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun buildFireWallFromRawInstructions(path: String): FireWall {
        val layers = rawInstructionConverter.convertRawInputIntoInstructions(path, this::buildLayer)
                .associateBy({ it.layer.depth }, { it })
        return FireWall(layers)
    }

    private fun buildLayer(rawLayer: String): StepWithLayer {
        val (depth, range) = rawLayer.splitIntoTwo(": ")
        return StepWithLayer(Layer(depth.toInt(), range.toInt()))
    }
}



