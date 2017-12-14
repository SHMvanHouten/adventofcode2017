package com.github.shmvanhouten.adventofcode2017.day14DiskDefragmentation

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DiskSpaceCalculatorTest {

    @Test
    fun `it should calculate which spaces are free or used for the test input`() {
        val calculator = DiskSpaceCalculator()
        assertThat(calculator.calculateAmountOfUsedSpaces("flqrgnkx"), equalTo(8108))
    }

    @Test
    fun `it should calculate which spaces are free or used for the challenge input`() {
        val calculator = DiskSpaceCalculator()
        assertThat(calculator.calculateAmountOfUsedSpaces("jxqlasbh"), equalTo(8140))
    }


}