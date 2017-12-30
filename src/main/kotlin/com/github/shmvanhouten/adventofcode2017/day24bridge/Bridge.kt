package com.github.shmvanhouten.adventofcode2017.day24bridge

data class Bridge(val components: List<BridgeComponent>, val openPort: Int) {

    val strength: Int by lazy { components.sumBy { it.firstPort + it.secondPort }
}
    val length: Int = components.size

    fun addComponent(component: BridgeComponent): Bridge {
        return Bridge(components.plus(component), component.getOtherPort(openPort))
    }

}