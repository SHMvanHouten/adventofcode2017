package com.github.shmvanhouten.adventofcode2017.day14DiskDefragmentation

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day10knothash.DenseKnotHasher

class DiskSpaceCalculator(private val knotHasher: DenseKnotHasher = DenseKnotHasher()) {
    fun calculateAmountOfUsedSpaces(key: String): Int {
        return (0.until(128)).sumBy { calculateUsedSpacesInRow(it, key) }
    }

    fun calculateAmountOfRegions(key: String): Int {
        val usedSpaces = (0.until(128)).flatMap { locateUsedSpaces(it, key) }

        var visitedCoordinates = setOf<Coordinate>()
        var amountOfRegions = 0

        usedSpaces.forEach { coordinate ->
            if (coordinate.getNeighbours().any { usedSpaces.contains(it) }) {
                if(visitedCoordinates.none { it == coordinate }) {
                    visitedCoordinates += findCompleteRegion(usedSpaces, coordinate)
                    amountOfRegions++
                }
            }else {
                // single space will count as a region
                amountOfRegions++
            }
            visitedCoordinates += coordinate
        }

        return amountOfRegions
    }

    private fun findCompleteRegion(usedRegions: List<Coordinate>, originalCoordinate: Coordinate): Set<Coordinate> {

        var unvisitedUseCoordinates = originalCoordinate.getNeighbours()
                .filter { neighbourCoordinate -> usedRegions.any { it == neighbourCoordinate } }.toSet()
        var visitedUsedCoordinates = setOf<Coordinate>()

        while (unvisitedUseCoordinates.isNotEmpty()){
            val currentCoordinate = unvisitedUseCoordinates.first()
            unvisitedUseCoordinates -= currentCoordinate

            val unvisitedNeighbours = currentCoordinate.getNeighbours()
                    .filter { neighbourCoordinate -> visitedUsedCoordinates.none { it == neighbourCoordinate } }
                    .filter { neighbourCoordinate -> usedRegions.any { it == neighbourCoordinate } }
                    .toSet()
            unvisitedUseCoordinates += unvisitedNeighbours

            visitedUsedCoordinates += currentCoordinate
        }

        return visitedUsedCoordinates
    }

    private fun locateUsedSpaces(rowNr: Int, key: String): List<Coordinate> {
        val row = getRowValues(rowNr, key)

        return row.mapIndexedNotNull { index, square ->
            if (square == '1') Coordinate(index, rowNr) else null
        }
    }

    private fun calculateUsedSpacesInRow(rowNr: Int, key: String): Int {
        val rowValues = getRowValues(rowNr, key)
        return rowValues.count { it == '1' }
    }

    private fun getRowValues(rowNr: Int, key: String): List<Char> {
        val rowKey = key + "-" + rowNr
        val hashedResult = knotHasher.performDenseHash(rowKey)
        return hashedResult.flatMap { convertToBinary(it) }
    }

    private fun convertToBinary(hexadecimalChar: Char): Iterable<Char> {
        val hexToInt = Integer.parseInt(hexadecimalChar.toString(), 16)

        return Integer.toBinaryString(hexToInt).asIterable()
    }
}