package com.github.shmvanhouten.adventofcode2017.day24bridge

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class LongestBridgeBuilderTest {


    @Test
    fun `it should build a bridge from these components to get a longest bridge with strength 19`() {
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
        val bridgeBuilder = LongestBridgeBuilder()
        assertThat(bridgeBuilder.buildMostSuitableBridge(bridgeComponents), equalTo(19))
    }

    @Test
    fun `it should solve the challenge input`() {
        val componentConverter = BridgeComponentConverter()
        val bridgeComponents = componentConverter.convertListOfComponents("/day24/day24.txt")
        val bridgeBuilder = LongestBridgeBuilder()
        assertThat(bridgeBuilder.buildMostSuitableBridge(bridgeComponents), equalTo(1994))
    }
}