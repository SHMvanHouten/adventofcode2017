package com.github.shmvanhouten.adventofcode2017.day24bridge

class BridgeBuilder {

    fun buildStrongestBridge(bridgeComponents: Iterable<BridgeComponent>): Int {
        return buildMostSuitableBridge(bridgeComponents, this::isCurrentBridgeStronger).strength
    }

    fun buildLongestBridge(bridgeComponents: Iterable<BridgeComponent>): Int {
        return buildMostSuitableBridge(bridgeComponents, this::isCurrentBridgeLonger).strength
    }

    private fun buildMostSuitableBridge(bridgeComponents: Iterable<BridgeComponent>, isCurrentBridgeBetter: (Bridge, Bridge) -> Boolean): Bridge {

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

        return bestBridge
    }

    private fun isCurrentBridgeLonger(currentBridge: Bridge, bestBridge: Bridge): Boolean {
        val currentLength = currentBridge.length
        val bestBridgeLength = bestBridge.length
        return currentLength > bestBridgeLength || currentLength == bestBridgeLength && isCurrentBridgeStronger(currentBridge, bestBridge)
    }

    private fun isCurrentBridgeStronger(currentBridge: Bridge, bestBridge: Bridge) =
            currentBridge.strength > bestBridge.strength

    private fun addMatchingComponentsToEnd(bridgeComponents: Iterable<BridgeComponent>, currentBridge: Bridge): List<Bridge> {
        return bridgeComponents
                .subtract(currentBridge.components)
                .filter { !it.matchPorts(0) }
                .filter { it.matchPorts(currentBridge.openPort) }
                .map { currentBridge.addComponent(it) }
    }

    private fun buildStartingBridges(bridgeComponents: Iterable<BridgeComponent>): MutableList<Bridge> {
        return bridgeComponents
                .filter { it.matchPorts(0) }
                .map({ Bridge(listOf(it), it.getOtherPort(0)) })
                .toMutableList()
    }
}

