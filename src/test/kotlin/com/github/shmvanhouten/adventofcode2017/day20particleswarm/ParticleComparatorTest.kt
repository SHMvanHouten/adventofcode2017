package com.github.shmvanhouten.adventofcode2017.day20particleswarm

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ParticleComparatorTest {

    @Test
    fun `it should compare the first and second particle and say the second is the smallest`() {
        val sortedParticles = setOf(
                Particle(1, Coordinate(0, 0, 0), Coordinate(0, 0, 0), Coordinate(-4, 2, 3)),
                Particle(2, Coordinate(0, 0, 0), Coordinate(0, 0, 0), Coordinate(2, 2, -3))
        ).sortedWith(ParticleComparator)
        assertThat(sortedParticles.first().number, equalTo(2))
    }

    @Test
    fun `it should compare the particles and say the second is the smallest`() {
        val sortedParticles = setOf(
                Particle(1, Coordinate(0, 0, 0), Coordinate(3, -10, 8), Coordinate(0, 0, 0)),
                Particle(2, Coordinate(0, 0, 0), Coordinate(2, -3, 3), Coordinate(0, 0, 0)),
                Particle(3, Coordinate(0, 0, 0), Coordinate(5, -4, -1), Coordinate(0, 0, 0))
        ).sortedWith(ParticleComparator)
        assertThat(sortedParticles.first().number, equalTo(2))
    }

    @Test
    fun `it should solve the challenge input`() {
        val swarmBuilder = ParticleSwarmBuilder()
        val sortedParticles = swarmBuilder.build("/day20/day20.txt").sortedWith(ParticleComparator)
        assertThat(sortedParticles.first().number, equalTo(2))
    }



}