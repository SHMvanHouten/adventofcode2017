package com.github.shmvanhouten.adventofcode2017.day22sprorificavirus

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Direction

abstract class VirusSimulator {

    fun countInfections(amountOfBursts: Int, infectedCoordinates: Map<Coordinate, InfectionStatus>): Int {

        val advancedVirusState = advanceVirusStateInBursts(infectedCoordinates, amountOfBursts)


        return advancedVirusState.amountOfInfections
    }

    private fun advanceVirusStateInBursts(infectedCoordinates: Map<Coordinate, InfectionStatus>, amountOfBursts: Int): VirusState {
        val affectedCoordinates = infectedCoordinates.toMutableMap()

        val virusState = VirusState(Coordinate(0, 0), Direction.NORTH, 0)

        return 0.until(amountOfBursts).fold(virusState, { advancedVirusState: VirusState, _ ->
            advanceBurst(affectedCoordinates, advancedVirusState)
        })
//        printGrid(affectedCoordinates.filter { it.value != CLEAN })
    }

    abstract fun advanceBurst(affectedCoordinates: MutableMap<Coordinate, InfectionStatus>, virusState: VirusState): VirusState
}


data class VirusState(val position: Coordinate, val direction: Direction, val amountOfInfections: Int)


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