package com.github.shmvanhouten.adventofcode2017.day14DiskDefragmentation

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DiskSquareCalculatorTest {

    @Test
    fun `it should calculate which spaces are used for the test input`() {
        val calculator = DiskSquareCalculator()
        assertThat(calculator.calculateAmountOfUsedSquares("flqrgnkx"), equalTo(8108))
    }

    @Test
    fun `it should calculate which spaces are used for the challenge input`() {
        val calculator = DiskSquareCalculator()
        assertThat(calculator.calculateAmountOfUsedSquares("jxqlasbh"), equalTo(8140))
    }


    @Test
    fun `it should calculate the amount of regions for the test input`() {
        val calculator = DiskSquareCalculator()
        assertThat(calculator.calculateAmountOfRegions("flqrgnkx"), equalTo(1242))
    }

    @Test
    fun `it should calculate the amount of regions for the challenge input`() {
        val calculator = DiskSquareCalculator()
        assertThat(calculator.calculateAmountOfRegions("jxqlasbh"), equalTo(1182))
    }


}