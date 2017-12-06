package com.github.shmvanhouten.adventofcode2017.day06memoryreallocation

data class MemoryBank(val number: Int, var amountOfBlocks: Int): Comparable<MemoryBank> {
    override fun compareTo(other: MemoryBank): Int {
        val amountsCompared = this.amountOfBlocks.compareTo(other.amountOfBlocks)
        return if(amountsCompared != 0){
            amountsCompared
        } else other.number.compareTo(this.number)
    }

    fun setToZero(){
        amountOfBlocks = 0
    }

    fun increment(){
        amountOfBlocks++
    }
}