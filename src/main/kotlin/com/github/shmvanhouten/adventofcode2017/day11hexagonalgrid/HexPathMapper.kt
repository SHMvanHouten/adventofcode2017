package com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid

import com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid.Direction.*

class HexPathMapper {

    fun takePath(rawStepsInput: String, startintCoordinate: HexCoordinate = HexCoordinate(0,0)): HexCoordinate {
        val steps = rawStepsInput.split(",")
                .map { parseDirection(it) }.asIterable()

        var currentPosition = startintCoordinate
        steps.forEach { direction ->
            currentPosition = currentPosition.getNeighbouringHexTo(direction)
        }

        return currentPosition
    }

    private fun parseDirection(rawDirection: String): Direction {
        return when (rawDirection) {
            "n" -> NORTH
            "ne" -> NORTH_EAST
            "se" -> SOUTH_EAST
            "s" -> SOUTH
            "sw" -> SOUTH_WEST
            "nw" -> NORTH_WEST
            else -> error("faulty input")
        }
    }


}

enum class Direction {
    NORTH,
    NORTH_EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    NORTH_WEST
}

