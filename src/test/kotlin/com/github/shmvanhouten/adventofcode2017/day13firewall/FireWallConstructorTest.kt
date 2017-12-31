package com.github.shmvanhouten.adventofcode2017.day13firewall

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class FireWallConstructorTest {

    @Test
    fun `it should construct the firewall from the raw input`() {
        val constructor = FireWallConstructor()
        val fireWall = constructor.buildFireWallFromRawInstructions("/day13/day13.txt")
        assertThat((fireWall.iterator().next() as StepWithLayer).layer.depth, equalTo(0))
        assertThat((fireWall.iterator().next() as StepWithLayer).layer.range, equalTo(2))
        assertThat((fireWall.iterator().next() as StepWithLayer).layer.range, equalTo(4))
    }
}