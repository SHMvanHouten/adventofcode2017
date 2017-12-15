package com.github.shmvanhouten.adventofcode2017.day14DiskDefragmentation

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day10knothash.DenseKnotHasher


class DiskBuilder(private val knotHasher: DenseKnotHasher = DenseKnotHasher()) {


    fun getListOfUsedSquares(key: String): List<Coordinate> {
        return (0.until(128)).flatMap { locateUsedSquares(it, key) }
    }

    fun calculateUsedSquaresInRow(rowNr: Int, key: String): Int {
        val rowValues = calculateUsedSquaresPerRow(rowNr, key)
        return rowValues.count { it == '1' }
    }

    private fun locateUsedSquares(rowNr: Int, key: String): List<Coordinate> {
        val row = calculateUsedSquaresPerRow(rowNr, key)

        return row.mapIndexedNotNull { index, square ->
            if (square == '1') Coordinate(index, rowNr) else null
        }
    }

    private fun calculateUsedSquaresPerRow(rowNr: Int, key: String): Iterable<Char> {
        val rowKey = key + "-" + rowNr
        val hashedResult = knotHasher.performDenseHash(rowKey)
        return hashedResult.flatMap { convertToBinary(it) }.asIterable()
    }

    private fun convertToBinary(hexadecimalChar: Char): Iterable<Char> {
        val hexToInt = Integer.parseInt(hexadecimalChar.toString(), 16)

        var binaryString = Integer.toBinaryString(hexToInt)
        while (binaryString.length < 4){
            binaryString = "0" + binaryString
        }
        return binaryString.asIterable()
    }
}