package com.github.shmvanhouten.adventofcode2017.day06memoryreallocation

class FloorOfMemoryBanks(val memoryBanks: List<MemoryBank>) {

    fun reallocateBiggestBank(): FloorOfMemoryBanks {
        val biggestMemoryBank = memoryBanks.max()!!

        var blocksToReallocate = biggestMemoryBank.amountOfBlocks
        biggestMemoryBank.setToZero()

        var index = getIndexToStart(biggestMemoryBank, memoryBanks.lastIndex)
        while (blocksToReallocate > 0) {
            memoryBanks[index].increment()

            if (index >= memoryBanks.lastIndex) {
                index = 0
            } else {
                index++
            }
            blocksToReallocate--
        }
        return this
    }

    private fun getIndexToStart(biggestMemoryBank: MemoryBank, lastIndex: Int): Int {
        val index = biggestMemoryBank.number + 1
        if(index > lastIndex) return 0
        return index
    }

    fun getCurrentStateOfFloors(): String{
        return memoryBanks.map { it.amountOfBlocks }.joinToString("")
    }
}