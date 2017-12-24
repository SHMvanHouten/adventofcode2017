package com.github.shmvanhouten.adventofcode2017.day24bridge

data class Bridge(private val components: List<BridgeComponent>, val openPort: Int, val usedComponents: Iterable<Int>) {

    val strength: Int = components.sumBy { it.firstPort + it.secondPort }

    val length: Int = components.size

    fun addComponent(component: BridgeComponent, componentId: Int): Bridge {
        return Bridge(components.plus(component), component.getOtherPort(openPort), usedComponents.plus(componentId))
    }

}