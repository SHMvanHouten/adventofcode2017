package com.github.shmvanhouten.adventofcode2017.day02corruptionchecksum

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ChecksumCalculatorDivisibleValuesImplTest {

    @Test
    fun `it should calculate a checksum of 4 for the input 5 9 2 8`() {
        val calculator = ChecksumCalculatorDivisibleValuesImpl()
        val input = "5 9 2 8"
        assertThat(calculator.calculateChecksum(input), equalTo(4))
    }

    @Test
    fun `it should calculate a checksum of 3 for the input 9 4 7 3`() {
        val calculator = ChecksumCalculatorDivisibleValuesImpl()
        val input = "9 4 7 3"
        assertThat(calculator.calculateChecksum(input), equalTo(3))
    }

    @Test
    fun `it should calculate a checksum of 4 for the multi-line input`() {
        val calculator = ChecksumCalculatorDivisibleValuesImpl()
        val input = """5 9 2 8
9 4 7 3
3 8 6 5"""
        assertThat(calculator.calculateChecksum(input), equalTo(9))
    }

    @Test
    fun `it should solve the challenge input`() {
        val calculator = ChecksumCalculatorDivisibleValuesImpl()

        assertThat(calculator.calculateChecksum(day02challengeInput), equalTo(242))
    }
}