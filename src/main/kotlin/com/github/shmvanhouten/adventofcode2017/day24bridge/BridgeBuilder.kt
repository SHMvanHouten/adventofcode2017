package com.github.shmvanhouten.adventofcode2017.day24bridge

abstract class BridgeBuilder {

    fun buildMostSuitableBridge(bridgeComponents: List<BridgeComponent>): Int {

        val uncompletedBridges = BridgeIterator(buildStartingBridges(bridgeComponents))
        var strongestBridge = Bridge(emptyList(), 0)

        while (uncompletedBridges.hasNext()) {
            val (currentBridge, visitedComponentIndexes) = uncompletedBridges.getNext()

            if (currentBridge.openPort == 0) {
                if (isCurrentBridgeBetter(currentBridge, strongestBridge)) strongestBridge = currentBridge
            } else {

                val newBridges = (0.until(bridgeComponents.size))
                        .filter { !visitedComponentIndexes.contains(it) }
                        .filter { bridgeComponents[it].matchPorts(currentBridge.openPort) }
                        .map{ currentBridge.addComponent(bridgeComponents[it]) to visitedComponentIndexes.plus(it) }


                if (newBridges.isEmpty()) {
                    if (isCurrentBridgeBetter(currentBridge, strongestBridge)) strongestBridge = currentBridge
                } else {
                    uncompletedBridges.addBridges(newBridges)
                }
            }

        }

        return strongestBridge.strength
    }

    abstract fun isCurrentBridgeBetter(currentBridge: Bridge, bestBridge: Bridge): Boolean

    private fun buildStartingBridges(bridgeComponents: List<BridgeComponent>): MutableList<Pair<Bridge, List<Int>>> {
        return bridgeComponents
                .withIndex()
                .filter { it.value.matchPorts(0) }
                .map({ Bridge(listOf(it.value), it.value.getOtherPort(0)) to listOf(it.index) })
                .toMutableList()
    }
}

