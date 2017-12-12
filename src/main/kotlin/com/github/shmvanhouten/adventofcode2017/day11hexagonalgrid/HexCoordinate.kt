package com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid

import com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid.Direction.*

data class HexCoordinate(val x: Int, val y: Int) {

    fun getNeighbouringHexTo(direction: Direction): HexCoordinate {
        return if (this.x % 2 == 0) {
            when (direction) {
                NORTH -> HexCoordinate(this.x, this.y - 1)
                SOUTH -> HexCoordinate(this.x, this.y + 1)
                NORTH_EAST -> HexCoordinate(this.x + 1, this.y)
                SOUTH_EAST -> HexCoordinate(this.x + 1, this.y + 1)
                SOUTH_WEST -> HexCoordinate(this.x - 1, this.y + 1)
                NORTH_WEST -> HexCoordinate(this.x - 1, this.y)
            }
        } else {
            when (direction) {
                NORTH -> HexCoordinate(this.x, this.y - 1)
                SOUTH -> HexCoordinate(this.x, this.y + 1)
                NORTH_EAST -> HexCoordinate(this.x + 1, this.y - 1)
                SOUTH_EAST -> HexCoordinate(this.x + 1, this.y)
                SOUTH_WEST -> HexCoordinate(this.x - 1, this.y)
                NORTH_WEST -> HexCoordinate(this.x - 1, this.y - 1)
            }
        }
    }
}