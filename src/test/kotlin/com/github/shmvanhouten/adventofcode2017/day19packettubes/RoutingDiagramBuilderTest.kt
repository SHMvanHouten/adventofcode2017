package com.github.shmvanhouten.adventofcode2017.day19packettubes

import com.github.shmvanhouten.adventofcode2017.day03spiralmemory.Coordinate
import com.github.shmvanhouten.adventofcode2017.day19packettubes.ComponentType.WAY_POINT
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class RoutingDiagramBuilderTest {

    @Test
    fun `it should convert the raw testDiagram to a RoutingDiagram`() {
        val diagramBuilder = RoutingDiagramBuilder()
        val routingDiagram = diagramBuilder.build("/day19/challengeTest.txt")
        val diagramComponent = routingDiagram.getComponentAt( Coordinate(5, 2))
        assertThat(diagramComponent.name, equalTo('A'))
        assertThat(diagramComponent.type, equalTo(WAY_POINT))
    }
}