package com.github.shmvanhouten.adventofcode2017.day06memoryreallocation

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class FloorOfMemoryBanksTest {

    @Test
    fun `it should redistribute the memory of the biggest memorybank among its neighbours`() {
        val floorBuilder = FloorBuilder()
        val rawInput = "0\t2\t7\t0"
        val floor = floorBuilder.buildFloorFromRawInput(rawInput)
        val floorAfterReAllocation = floor.reallocateBiggestBank()
        assertThat(floorAfterReAllocation.memoryBanks[2].amountOfBlocks, equalTo(1))
        println(floorAfterReAllocation.memoryBanks)
    }
}