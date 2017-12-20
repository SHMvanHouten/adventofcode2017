package com.github.shmvanhouten.adventofcode2017.day19packettubes

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate

class RoutingDiagram(private val components: Map<Coordinate, DiagramComponent>) {

    fun getStartingPoint(): DiagramComponent {
        return components.values.find { it.coordinate.y == 0 }!!
    }

    fun componentIsPresent(coordinate: Coordinate): Boolean {
        return components.containsKey(coordinate)
    }

    fun getComponentAt(coordinate: Coordinate): DiagramComponent {
        return components.getValue(coordinate)
    }
}