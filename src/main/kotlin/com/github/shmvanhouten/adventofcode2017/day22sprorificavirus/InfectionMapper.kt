package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class InfectionMapper(private val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun listAllInfectionsOnGrid(path: String): Set<Coordinate> {
        return rawInstructionConverter.convertIndexedRawInputIntoInstructions(path, this::getInfectionsOnRow)
                .flatMap { it }
                .toSet()
    }

    private fun getInfectionsOnRow(readline: String, y: Int): List<Coordinate> {
        val middleOfGrid = readline.length / 2
        return readline.mapIndexedNotNull { x, char ->
            if(char == '#'){
                Coordinate(x - middleOfGrid, y - middleOfGrid)
            } else {
                null
            }
        }
    }
}