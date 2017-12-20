package com.github.shmvanhouten.adventofcode2017.day19packettubes

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate

class RoutingDiagram(val components: Set<DiagramComponent>) {

    fun getStartingPoint(): DiagramComponent {
        return components.find { it.coordinate.y == 0 }!!
    }

    fun getComponentAt(coordinate: Coordinate): DiagramComponent? {
        return components.find { it.coordinate == coordinate }
    }
}