package com.github.shmvanhouten.adventofcode2017.day20particleswarm

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class ParticleSwarmBuilder(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun build(path: String): List<Particle> {
        return rawInstructionConverter.convertIndexedRawInputIntoInstructions(path, this::buildParticle)
    }

    private fun buildParticle(readline: String, particleNumber: Int): Particle{
        val coordinates = readline.split(" ")
                .map { convertToCoordinate(it) }
        return Particle(particleNumber, coordinates[0], coordinates[1], coordinates[2])
    }

    private fun convertToCoordinate(rawCoordinate: String): Coordinate {
        val coordinateValues = rawCoordinate
                .substring(rawCoordinate.indexOf('<') + 1, rawCoordinate.indexOf('>'))
                .split(",")
        return Coordinate(coordinateValues[0].toInt(), coordinateValues[1].toInt(), coordinateValues[2].toInt())
    }
}