package com.github.shmvanhouten.adventofcode2017.day06memoryreallocation

class FloorBuilder {

    fun buildFloorFromRawInput(rawInput: String): FloorOfMemoryBanks {

        val memoryBanks = rawInput
                .split('\t')
                .mapIndexed { index, rawValue -> MemoryBank(index, rawValue.toInt()) }
        return FloorOfMemoryBanks(memoryBanks)
    }
}