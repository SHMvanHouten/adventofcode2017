package com.github.shmvanhouten.adventofcode2017.day24bridge

abstract class BridgeBuilder {

    fun buildMostSuitableBridge(bridgeComponents: Iterable<BridgeComponent>): Int {

        val uncompletedBridges = BridgeIterator(buildStartingBridges(bridgeComponents))
        var bestBridge = Bridge(emptyList(), 0)

        while (uncompletedBridges.hasNext()) {
            val currentBridge = uncompletedBridges.getNext()
            val newBridges = addMatchingComponentsToEnd(bridgeComponents, currentBridge)

            if (newBridges.isEmpty()) {
                if (isCurrentBridgeBetter(currentBridge, bestBridge)) bestBridge = currentBridge
            } else {
                uncompletedBridges.addBridges(newBridges)
            }
        }

        return bestBridge.strength
    }

    private fun addMatchingComponentsToEnd(bridgeComponents: Iterable<BridgeComponent>, currentBridge: Bridge): List<Bridge> {
        return bridgeComponents
                .subtract(currentBridge.components)
                .filter { !it.matchPorts(0) }
                .filter { it.matchPorts(currentBridge.openPort) }
                .map { currentBridge.addComponent(it) }
    }

    abstract fun isCurrentBridgeBetter(currentBridge: Bridge, bestBridge: Bridge): Boolean

    private fun buildStartingBridges(bridgeComponents: Iterable<BridgeComponent>): MutableList<Bridge> {
        return bridgeComponents
                .filter { it.matchPorts(0) }
                .map({ Bridge(listOf(it), it.getOtherPort(0)) })
                .toMutableList()
    }
}

