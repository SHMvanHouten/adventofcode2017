package com.github.shmvanhouten.adventofcode2017.day14DiskDefragmentation

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate

class DiskSpaceCalculator(private val diskBuilder: DiskBuilder = DiskBuilder()) {

    fun calculateAmountOfUsedSpaces(key: String): Int {
        return (0.until(128)).sumBy { diskBuilder.calculateUsedSpacesInRow(it, key) }
    }

    fun calculateAmountOfRegions(key: String): Int {
        val usedSpaces = diskBuilder.getListOfUsedSpaces(key)

        var visitedCoordinates = setOf<Coordinate>()
        var amountOfRegions = 0

        usedSpaces.forEach { coordinate ->
            if (coordinate.getNeighbours().any { usedSpaces.contains(it) }) {
                if (visitedCoordinates.none { it == coordinate }) {
                    visitedCoordinates += findCompleteRegion(usedSpaces, coordinate)
                    amountOfRegions++
                }
            } else {
                // single space will count as a region
                amountOfRegions++
            }
            visitedCoordinates += coordinate
        }

        return amountOfRegions
    }


    private fun findCompleteRegion(usedSpaces: List<Coordinate>, originalCoordinate: Coordinate): Set<Coordinate> {

        var unvisitedCoordinates = setOf(originalCoordinate)
        var visitedCoordinates = setOf<Coordinate>()

        while (unvisitedCoordinates.isNotEmpty()) {
            val currentCoordinate = unvisitedCoordinates.first()
            unvisitedCoordinates -= currentCoordinate
            visitedCoordinates += currentCoordinate

            unvisitedCoordinates += currentCoordinate.getNeighbours().filter { neighbour -> usedSpaces.contains(neighbour) }
                    .filter { neighbour -> !visitedCoordinates.contains(neighbour) }
                    .filter { neighbour -> !unvisitedCoordinates.contains(neighbour) }
        }

        return visitedCoordinates
    }
}

