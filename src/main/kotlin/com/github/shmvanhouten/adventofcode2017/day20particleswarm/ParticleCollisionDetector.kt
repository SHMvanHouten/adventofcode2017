package com.github.shmvanhouten.adventofcode2017.day20particleswarm

class ParticleCollisionDetector(private val coordinateToParticles: Map<Coordinate, Particle>) {

    fun getAmountOfParticlesLeftAfterCollision(amountOfIterations: Int): Int {
        var particlesMap = coordinateToParticles

        0.until(amountOfIterations).forEach {
            var newParticlesMap = mapOf<Coordinate, Particle>()
            var deleteParticles = mapOf<Coordinate, Particle>()

            particlesMap.values.forEach { oldParticle ->
                val advancedParticle = advanceTick(oldParticle)
                val location = advancedParticle.location
                if (newParticlesMap.contains(location) || deleteParticles.contains(location)) {
                    deleteParticles += location to advancedParticle
                    newParticlesMap -= location
                } else {
                    newParticlesMap += location to advancedParticle
                }
                particlesMap = newParticlesMap
            }
        }
        return particlesMap.size
    }

    private fun advanceTick(particle: Particle): Particle {
        val newVelocity = particle.velocity + particle.acceleration
        val newLocation = particle.location + newVelocity
        return Particle(particle.number, newLocation, newVelocity, particle.acceleration)
    }


}