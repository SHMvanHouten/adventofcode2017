package com.github.shmvanhouten.adventofcode2017.day24bridge

abstract class BridgeBuilder {

    fun buildMostSuitableBridge(bridgeComponents: Iterable<BridgeComponent>): Int {

        val uncompletedBridges = BridgeIterator(buildStartingBridges(bridgeComponents))
        var bestBridge = Bridge(emptyList(), 0, emptyList())

        while (uncompletedBridges.hasNext()) {
            val currentBridge = uncompletedBridges.getNext()

            if (currentBridge.openPort == 0) {
                if (isCurrentBridgeBetter(currentBridge, bestBridge)) bestBridge = currentBridge
            } else {
                val newBridges = addMatchingComponentsToEnd(bridgeComponents, currentBridge)

                if (newBridges.isEmpty()) {
                    if (isCurrentBridgeBetter(currentBridge, bestBridge)) bestBridge = currentBridge
                } else {
                    uncompletedBridges.addBridges(newBridges)
                }
            }
        }

        return bestBridge.strength
    }

    private fun addMatchingComponentsToEnd(bridgeComponents: Iterable<BridgeComponent>, currentBridge: Bridge): List<Bridge> {
        return bridgeComponents
                .withIndex()
                .filter { !currentBridge.usedComponents.contains(it.index) }
                .filter { it.value.matchPorts(currentBridge.openPort) }
                .map { currentBridge.addComponent(it.value, it.index) }
    }

    abstract fun isCurrentBridgeBetter(currentBridge: Bridge, bestBridge: Bridge): Boolean

    private fun buildStartingBridges(bridgeComponents: Iterable<BridgeComponent>): MutableList<Bridge> {
        return bridgeComponents
                .withIndex()
                .filter { it.value.matchPorts(0) }
                .map({ Bridge(listOf(it.value), it.value.getOtherPort(0), listOf(it.index))})
                .toMutableList()
    }
}

