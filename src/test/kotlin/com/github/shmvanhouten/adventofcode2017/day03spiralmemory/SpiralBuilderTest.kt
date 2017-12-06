package com.github.shmvanhouten.adventofcode2017.day03spiralmemory

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class SpiralBuilderTest {

    @Test
    fun `it should build a spiral of 12 nodes`() {
        val builder = SpiralBuilderNoValuesImpl()
        val spiral: Spiral = builder.buildSpiral(12)
        assertThat(spiral.getNode(10)?.coordinate, equalTo(Coordinate(2,1)))
        assertThat(spiral.getNode(12)?.coordinate, equalTo(Coordinate(2, -1)))
    }
}