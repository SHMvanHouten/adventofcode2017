package com.github.shmvanhouten.adventofcode2017.day20particleswarm

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ParticleSwarmBuilderTest {

    @Test
    fun `it should parse the strings to Particles`() {
        val particleSwarmBuilder = ParticleSwarmBuilder()
        val particles = particleSwarmBuilder.build("/day20/day20.txt")
        println(particles[0])
        assertThat(particles.size, equalTo(1000))
    }
}