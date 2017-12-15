package com.github.shmvanhouten.adventofcode2017.day14DiskDefragmentation

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate

class DiskSquareCalculator(private val diskBuilder: DiskBuilder = DiskBuilder()) {

    fun calculateAmountOfUsedSquares(key: String): Int {
        return (0.until(128)).sumBy { diskBuilder.calculateUsedSquaresInRow(it, key) }
    }

    fun calculateAmountOfRegions(key: String): Int {
        val usedSquares = diskBuilder.getListOfUsedSquares(key)

        var visitedCoordinates = setOf<Coordinate>()
        var amountOfRegions = 0

        usedSquares.forEach { coordinate ->
            if (coordinate.getNeighbours().any { usedSquares.contains(it) }) {
                if (visitedCoordinates.none { it == coordinate }) {
                    visitedCoordinates += findCompleteRegion(usedSquares, coordinate)
                    amountOfRegions++
                }
            } else {
                // single square will count as a region
                amountOfRegions++
            }
            visitedCoordinates += coordinate
        }

        return amountOfRegions
    }


    private fun findCompleteRegion(usedSquares: List<Coordinate>, originalCoordinate: Coordinate): Set<Coordinate> {

        var unvisitedCoordinates = setOf(originalCoordinate)
        var visitedCoordinates = setOf<Coordinate>()

        while (unvisitedCoordinates.isNotEmpty()) {
            val currentCoordinate = unvisitedCoordinates.first()
            unvisitedCoordinates -= currentCoordinate
            visitedCoordinates += currentCoordinate

            unvisitedCoordinates += currentCoordinate.getNeighbours().filter { neighbour -> usedSquares.contains(neighbour) }
                    .filter { neighbour -> !visitedCoordinates.contains(neighbour) }
                    .filter { neighbour -> !unvisitedCoordinates.contains(neighbour) }
        }

        return visitedCoordinates
    }
}

