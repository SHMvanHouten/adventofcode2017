package com.github.shmvanhouten.adventofcode2017.day19packettubes

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day19packettubes.ComponentType.*
import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter

class RoutingDiagramBuilder(private val instructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun build(path: String): RoutingDiagram {
        val components = instructionConverter.convertIndexedRawInputIntoInstructions(path, this::convertRow).flatMap { it }.toSet()
        return RoutingDiagram(components)
    }

    private fun convertRow(readline: String, rowNumber: Int): List<DiagramComponent>{
        return readline.mapIndexedNotNull { index, char ->
            val coordinate = Coordinate(index, rowNumber)
            when(char){
                ' ' -> null
                '-', '|' -> DiagramComponent(PATH, coordinate)
                '+' -> DiagramComponent(TURN, coordinate)
                else -> DiagramComponent(WAY_POINT, coordinate, char)
            }
        }
    }
}