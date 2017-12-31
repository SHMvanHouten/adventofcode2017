package com.github.shmvanhouten.adventofcode2017.day13firewall

interface Step {
    fun hasLayer(): Boolean
}

data class StepWithLayer(val layer: Layer) : Step {
    override fun hasLayer() = true
}

class EmptyStep : Step{
    override fun hasLayer() = false
}

data class Layer(
        val depth: Int,
        val range: Int
)

fun emptyStep(): EmptyStep {
    return EmptyStep()
}