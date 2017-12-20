package com.github.shmvanhouten.adventofcode2017.day20particleswarm

import kotlin.math.absoluteValue

data class Particle(val number: Int, val location: Coordinate, val velocity: Coordinate, val acceleration: Coordinate){
}

data class Coordinate(val x: Int, val y: Int, val z: Int):Comparable<Coordinate> {
    override fun compareTo(other: Coordinate): Int {
        val totalValueOfThis = this.x.absoluteValue + this.y.absoluteValue + this.z.absoluteValue
        val totalValueOfOther = other.x.absoluteValue + other.y.absoluteValue + other.z.absoluteValue
        return totalValueOfThis.compareTo(totalValueOfOther)
    }

    operator fun plus(otherCoordinate: Coordinate): Coordinate {
        val x = this.x + otherCoordinate.x
        val y = this.y + otherCoordinate.y
        val z = this.z + otherCoordinate.z
        return Coordinate(x, y, z)
    }
}