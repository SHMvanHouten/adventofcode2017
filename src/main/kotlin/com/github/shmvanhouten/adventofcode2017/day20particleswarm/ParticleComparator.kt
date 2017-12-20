package com.github.shmvanhouten.adventofcode2017.day20particleswarm

object ParticleComparator: Comparator<Particle> {
    override fun compare(first: Particle?, second: Particle?): Int {
        if(first != null && second != null) {
            val byAcceleration = first.acceleration.compareTo(second.acceleration)
            if(byAcceleration != 0){
                return byAcceleration
            }
            val byVelocity = first.velocity.compareTo(second.velocity)
            if(byVelocity != 0){
                return byVelocity
            }
            //todo: when acceleration and velocity are the same the comparison depends on which way the velocity is going compared to the location
            return first.location.compareTo(second.location)
        }
        return 0
    }
}