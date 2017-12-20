package com.github.shmvanhouten.adventofcode2017.day19packettubes

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class PacketPathFinderTest {

    @Test
    fun `it should find its path from A to B`() {
        val diagramBuilder = RoutingDiagramBuilder()
        val routingDiagram = diagramBuilder.build("/day19/test1.txt")

        val pathFinder = PacketPathFinder(routingDiagram)
        assertThat(pathFinder.getWayPointsAlongPath(), equalTo("AB"))
    }

    @Test
    fun `it should find its path from A to B to C turning left to C`() {
        val diagramBuilder = RoutingDiagramBuilder()
        val routingDiagram = diagramBuilder.build("/day19/test2.txt")

        val pathFinder = PacketPathFinder(routingDiagram)
        assertThat(pathFinder.getWayPointsAlongPath(), equalTo("ABC"))
    }

    @Test
    fun `it should find its path from A to B to C turning both ways`() {
        val diagramBuilder = RoutingDiagramBuilder()
        val routingDiagram = diagramBuilder.build("/day19/test2.txt")

        val pathFinder = PacketPathFinder(routingDiagram)
        assertThat(pathFinder.getWayPointsAlongPath(), equalTo("ABC"))
    }

    @Test
    fun `it should find its path from A through F for the challenge test`() {
        val diagramBuilder = RoutingDiagramBuilder()
        val routingDiagram = diagramBuilder.build("/day19/challengeTest.txt")

        val pathFinder = PacketPathFinder(routingDiagram)
        assertThat(pathFinder.getWayPointsAlongPath(), equalTo("ABCDEF"))
    }

    @Test
    fun `it should find its path for the challenge input`() {
        val diagramBuilder = RoutingDiagramBuilder()
        val routingDiagram = diagramBuilder.build("/day19/day19.txt")

        val pathFinder = PacketPathFinder(routingDiagram)
        assertThat(pathFinder.getWayPointsAlongPath(), equalTo("EPYDUXANIT"))
    }

    @Test
    fun `it should find the amount of steps from A through F for the challenge test`() {
        val diagramBuilder = RoutingDiagramBuilder()
        val routingDiagram = diagramBuilder.build("/day19/challengeTest.txt")

        val pathFinder = PacketPathFinder(routingDiagram)
        assertThat(pathFinder.getAmountOfStepsTaken(), equalTo(38))
    }


    @Test
    fun `it should find its steps taken for the challenge input`() {
        val diagramBuilder = RoutingDiagramBuilder()
        val routingDiagram = diagramBuilder.build("/day19/day19.txt")

        val pathFinder = PacketPathFinder(routingDiagram)
        assertThat(pathFinder.getAmountOfStepsTaken(), equalTo(17544))
    }


}