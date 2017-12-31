package com.github.shmvanhouten.adventofcode2017.day13firewall

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class FireWallTraverserTest {

    @Test
    fun `it should traverse the Firewall and hit a severity of 4`() {
        val layer = Layer(2,2)
        val layers = setOf(layer)
        val fireWall = FireWall(layers)
        val traverser = FireWallTraverser(fireWall)
        assertThat(traverser.getSeverity(), equalTo(4))
    }

    @Test
    fun `it should solve the test input and get 26 severity`() {
        val constructor = FireWallConstructor()
        val fireWall = constructor.buildFireWallFromRawInstructions("/day13/day13Test.txt")
        val traverser = FireWallTraverser(fireWall)
        assertThat(traverser.getSeverity(), equalTo(24))
    }

    @Test
    fun `it should solve the challenge input`() {
        val constructor = FireWallConstructor()
        val fireWall = constructor.buildFireWallFromRawInstructions("/day13/day13.txt")
        val traverser = FireWallTraverser(fireWall)
        assertThat(traverser.getSeverity(), equalTo(1728))
    }

    @Test
    fun `it should find the time to wait for the traverser to get through the firewall with 0 severity is 10 ps`() {
        val constructor = FireWallConstructor()
        val fireWall = constructor.buildFireWallFromRawInstructions("/day13/day13Test.txt")
        val traverser = FireWallTraverser(fireWall)
        assertThat(traverser.getTimeToWaitForUndetectedTraversal(), equalTo(10))
    }

    @Test
    fun `it should solve the challenge input for part 2`() {
        val constructor = FireWallConstructor()
        val fireWall = constructor.buildFireWallFromRawInstructions("/day13/day13.txt")
        val traverser = FireWallTraverser(fireWall)
        assertThat(traverser.getTimeToWaitForUndetectedTraversal(), equalTo(3946838))
    }
}