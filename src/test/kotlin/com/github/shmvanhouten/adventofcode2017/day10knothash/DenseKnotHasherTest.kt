package com.github.shmvanhouten.adventofcode2017.day10knothash

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DenseKnotHasherTest {

    @Test
    fun `it should hash an empty string`() {
        val hasher = DenseKnotHasher()
        assertThat(hasher.performDenseHash(inputLengths = ""), equalTo("a2582a3a0e66e6e86e3812dcb672a272"))
    }

    @Test
    fun `it should hash the string 'AoC 2017'`() {
        val hasher = DenseKnotHasher()
        assertThat(hasher.performDenseHash(inputLengths = "AoC 2017"), equalTo("33efeb34ea91902bb2f59c9920caa6cd"))
    }


    @Test
    fun `it should hash the string '1,2,3'`() {
        val hasher = DenseKnotHasher()
        assertThat(hasher.performDenseHash(inputLengths = "1,2,3"), equalTo("3efbe78a8d82f29979031a4aa0b16a9d"))
    }

    @Test
    fun `it should hash an the string '1,2,4'`() {
        val hasher = DenseKnotHasher()
        assertThat(hasher.performDenseHash(inputLengths = "1,2,4"), equalTo("63960835bcdc130f0b66d7ff4f6a5a8e"))
    }

    @Test
    fun `it should hash the challenge input`() {
        val hasher = DenseKnotHasher()
        assertThat(hasher.performDenseHash(inputLengths = "106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36"), equalTo("7adfd64c2a03a4968cf708d1b7fd418d"))
    }
}