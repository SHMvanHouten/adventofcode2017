package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate

interface VirusSimulator {

    fun countInfections(amountOfBursts: Int, infectedCoordinates: Map<Coordinate, InfectionStatus>): Int
}


enum class InfectionStatus {
    CLEAN,
    WEAKENED,
    INFECTED,
    FLAGGED;

    fun advanceInfectionStatus(): InfectionStatus {
        return if (this == FLAGGED) {
            CLEAN
        } else {
            InfectionStatus.values()[this.ordinal + 1]
        }
    }
}

fun printGrid(infectionsOnGrid: Map<Coordinate, InfectionStatus>) {
    val minY = infectionsOnGrid.keys.minBy { it.y }!!.y
    val minX = infectionsOnGrid.keys.minBy { it.x }!!.x


    val maxY = infectionsOnGrid.keys.maxBy { it.y }!!.y + 1
    val maxX = infectionsOnGrid.keys.maxBy { it.x }!!.x + 1
    minY.until(maxY).forEach { y ->
        val row = StringBuilder()
        minX.until(maxX).forEach { x ->
            if (infectionsOnGrid.contains(Coordinate(x, y))) {
                when(infectionsOnGrid.getValue(Coordinate(x,y))){
                    InfectionStatus.CLEAN -> row.append('.')
                    InfectionStatus.WEAKENED -> row.append('w')
                    InfectionStatus.INFECTED -> row.append('#')
                    InfectionStatus.FLAGGED -> row.append('f')
                }
            } else {
                row.append('.')
            }
        }
        println(row.toString())
    }
}