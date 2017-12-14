package com.github.shmvanhouten.adventofcode2017.day14DiskDefragmentation

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day10knothash.DenseKnotHasher


class DiskBuilder(private val knotHasher: DenseKnotHasher = DenseKnotHasher()) {


    fun getListOfUsedSpaces(key: String): List<Coordinate> {
        return (0.until(128)).flatMap { locateUsedSpaces(it, key) }
    }

    internal fun calculateUsedSpacesInRow(rowNr: Int, key: String): Int {
        val rowValues = getRowValues(rowNr, key)
        return rowValues.count { it == '1' }
    }

    private fun locateUsedSpaces(rowNr: Int, key: String): List<Coordinate> {
        val row = getRowValues(rowNr, key)

        return row.mapIndexedNotNull { index, square ->
            if (square == '1') Coordinate(index, rowNr) else null
        }
    }

    private fun getRowValues(rowNr: Int, key: String): List<Char> {
        val rowKey = key + "-" + rowNr
        val hashedResult = knotHasher.performDenseHash(rowKey)
        return hashedResult.flatMap { convertToBinary(it) }
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