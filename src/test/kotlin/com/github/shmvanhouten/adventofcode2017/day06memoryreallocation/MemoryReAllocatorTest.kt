package com.github.shmvanhouten.adventofcode2017.day06memoryreallocation

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class MemoryReAllocatorTest {

    @Test
    fun `it should find how many re-allocations happen before it finds a duplicate state`() {
        val reAllocator = MemoryReAllocator()
        val rawInput = "0\t2\t7\t0"
        assertThat(reAllocator.reallocateMemoryUntilDuplicateSituationOccurs(rawInput), equalTo(5))
    }

    @Test
    fun `it should solve the challenge input`() {
        val reAllocator = MemoryReAllocator()

        assertThat(reAllocator.reallocateMemoryUntilDuplicateSituationOccurs(day06ChallengeInput), equalTo(4074))
    }
}

val day06ChallengeInput = "11\t11\t13\t7\t0\t15\t5\t5\t4\t4\t1\t1\t7\t1\t15\t11"