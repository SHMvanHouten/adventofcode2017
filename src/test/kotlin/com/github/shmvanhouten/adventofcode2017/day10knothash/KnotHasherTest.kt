package com.github.shmvanhouten.adventofcode2017.day10knothash

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class KnotHasherTest {

    @Test
    fun `it should reverse the first three chars in a range of 0 to 4`() {
        val hasher = KnotHasher()
        assertThat(hasher.createHash(5, setOf(3)), equalTo(listOf(2,1,0,3,4)))
    }

    @Test
    fun `it should reverse the all five chars in a range of 0 to 4`() {
        val hasher = KnotHasher()
        assertThat(hasher.createHash(5, setOf(5)), equalTo(listOf(4,3,2,1,0)))
    }


    @Test
    fun `it should reverse the first three chars and then all 5 chars starting from index 3 `() {
        val hasher = KnotHasher()
        assertThat(hasher.createHash(5, setOf(3, 4)), equalTo(listOf(4,3,0,1,2)))
    }

    @Test
    fun `it should solve the test input `() {
        val hasher = KnotHasher()
        assertThat(hasher.createHash(5, setOf(3, 4, 1, 5)), equalTo(listOf(3, 4, 2, 1, 0)))
    }

    @Test
    fun `it should solve the challenge input `() {
        val hasher = KnotHasher()
        assertThat(hasher.multiplyFirstTwoNumbersInHash(256, day10ChallengeInput), equalTo(11413))
    }


}
val day10ChallengeInput = setOf(106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36)