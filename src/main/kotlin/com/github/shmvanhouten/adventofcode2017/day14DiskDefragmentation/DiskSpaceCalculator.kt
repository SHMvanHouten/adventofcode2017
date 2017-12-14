package com.github.shmvanhouten.adventofcode2017.day14DiskDefragmentation

import com.github.shmvanhouten.adventofcode2017.day10knothash.DenseKnotHasher

class DiskSpaceCalculator(private val knotHasher: DenseKnotHasher = DenseKnotHasher()) {
    fun calculateAmountOfUsedSpaces(key: String): Int {
        return (0.until(128)).sumBy { calculateUsedSpacesInRow(it, key) }
    }

    private fun calculateUsedSpacesInRow(rowNr: Int, key: String): Int {
        val stringToHash = key + "-" + rowNr
        val hashedResult = knotHasher.performDenseHash(stringToHash)
        return hashedResult.flatMap { convertToBinary(it) }
                .count { it == '1' }
    }

    private fun convertToBinary(hexadecimalChar: Char): List<Char> {
        val hexToInt = Integer.parseInt(hexadecimalChar.toString(), 16)

        return Integer.toBinaryString(hexToInt).toList()
    }
}