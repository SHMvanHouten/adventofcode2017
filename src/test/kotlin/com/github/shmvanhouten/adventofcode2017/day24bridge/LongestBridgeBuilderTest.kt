package com.github.shmvanhouten.adventofcode2017.day24bridge

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class LongestBridgeBuilderTest {


    @Test
    fun `it should solve the challenge input`() {
        val componentConverter = BridgeComponentConverter()
        val bridgeComponents = componentConverter.convertListOfComponents("/day24/day24.txt")
        val bridgeBuilder = LongestBridgeBuilder()
        assertThat(bridgeBuilder.buildMostSuitableBridge(bridgeComponents), equalTo(1994))
    }
}