package com.github.shmvanhouten.adventofcode2017.day24bridge

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class StrongestBridgeBuilderTest {

    @Test
    fun `it should build a bridge from the two components`() {
        val bridgeComponents = listOf(
                BridgeComponent(0, 0, 2),
                BridgeComponent(1, 2, 4))
        val bridgeBuilder = StrongestBridgeBuilder()
        assertThat(bridgeBuilder.buildMostSuitableBridge(bridgeComponents), equalTo(8))
    }

    @Test
    fun `it should build a bridge from two of the three components`() {
        val bridgeComponents = listOf(
                BridgeComponent(0, 0, 2),
                BridgeComponent(1, 2, 4),
                BridgeComponent(3, 3, 5))
        val bridgeBuilder = StrongestBridgeBuilder()
        assertThat(bridgeBuilder.buildMostSuitableBridge(bridgeComponents), equalTo(8))
    }

    @Test
    fun `it should build a bridge from these components to get a strongest bridge of 31`() {
        val bridgeComponents = listOf(
                BridgeComponent(0, 0, 2),
                BridgeComponent(1, 2, 2),
                BridgeComponent(2, 2, 3),
                BridgeComponent(3, 3, 4),
                BridgeComponent(4, 3, 5),
                BridgeComponent(5, 0, 1),
                BridgeComponent(6, 10, 1),
                BridgeComponent(7, 9, 10)
        )
        val bridgeBuilder = StrongestBridgeBuilder()
        assertThat(bridgeBuilder.buildMostSuitableBridge(bridgeComponents), equalTo(31))
    }

    @Test
    fun `it should solve the challenge input`() {
        val componentConverter = BridgeComponentConverter()
        val bridgeComponents = componentConverter.convertListOfComponents("/day24/day24.txt")
        val bridgeBuilder = StrongestBridgeBuilder()
        assertThat(bridgeBuilder.buildMostSuitableBridge(bridgeComponents), equalTo(2006))
    }




}