package com.github.shmvanhouten.adventofcode2017.day24bridge

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class BridgeComponentConverterTest {

    @Test
    fun `it should build BridgeComponents from the challenge input strings`() {
        val componentConverter = BridgeComponentConverter()
        val listOfComponents = componentConverter.convertListOfComponents("/day24/day24.txt")
                .toList()
        assertThat(listOfComponents[0].firstPort, equalTo(24))
        assertThat(listOfComponents.size, equalTo(57))
    }
}
