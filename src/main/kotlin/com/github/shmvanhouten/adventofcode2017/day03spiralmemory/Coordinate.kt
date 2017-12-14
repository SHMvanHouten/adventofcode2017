package com.github.shmvanhouten.adventofcode2017.day03spiralmemory

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.RelativePosition.*

data class Coordinate(val x: Int, val y: Int){
    fun getNeighbours(): Set<Coordinate> {
        return setOf(
        this + TOP.coordinate,
        this + RIGHT.coordinate,
        this + BOTTOM.coordinate,
        this + LEFT.coordinate
        )
    }

    operator fun plus(otherCoordinate: Coordinate): Coordinate {
        val x = this.x + otherCoordinate.x
        val y = this.y + otherCoordinate.y
        return Coordinate(x, y)
    }
}