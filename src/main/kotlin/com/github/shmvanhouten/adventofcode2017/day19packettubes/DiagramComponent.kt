package com.github.shmvanhouten.adventofcode2017.day19packettubes

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate

data class DiagramComponent(val type: ComponentType, val coordinate: Coordinate, val name: Char = '_')

enum class ComponentType {
    PATH,
    WAY_POINT,
    TURN
}