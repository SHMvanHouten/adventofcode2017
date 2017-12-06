package com.github.shmvanhouten.adventofcode2017.day06memoryreallocation

class FloorBuilder {

    fun buildFloorFromRawInput(rawInput: String): FloorOfMemoryBanks {
        var index = 0
        val memoryBanks = rawInput.split('\t')
                .map { MemoryBank(index++, it.toInt()) }
        return FloorOfMemoryBanks(memoryBanks)
    }
}