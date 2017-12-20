package com.github.shmvanhouten.adventofcode2017.day20particleswarm

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ParticleCollisionDetectorTest {

    @Test
    fun `it should remove all the collided particles`() {
        val particleSwarmBuilder = ParticleSwarmBuilder()
        val particleMap = particleSwarmBuilder.buildLocationToParticleMap("/day20/test1.txt")

        val collisionDetector = ParticleCollisionDetector(particleMap)
        assertThat(collisionDetector.getAmountOfParticlesLeftAfterCollision(10), equalTo(1))
    }

    @Test
    fun `it should solve the challenge input`() {
        val particleSwarmBuilder = ParticleSwarmBuilder()
        val particleMap = particleSwarmBuilder.buildLocationToParticleMap("/day20/day20.txt")

        val collisionDetector = ParticleCollisionDetector(particleMap)
        assertThat(collisionDetector.getAmountOfParticlesLeftAfterCollision(1000), equalTo(461))
    }


}