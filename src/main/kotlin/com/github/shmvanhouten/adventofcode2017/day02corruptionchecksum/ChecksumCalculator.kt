package com.github.shmvanhouten.adventofcode2017.day02corruptionchecksum

abstract class ChecksumCalculator {

    fun calculateChecksum(rawInput: String): Int {
        val rows = rawInput.split("\n")
        return rows.sumBy { calculateRowChecksum(it) }
    }

    abstract fun calculateRowChecksum(rawInput: String): Int


    fun getSortedRowValues(rawInput: String): List<Int> {
        return rawInput
                .split("\t", " ")
                .map { it.toInt() }
                .sortedDescending()
    }

}