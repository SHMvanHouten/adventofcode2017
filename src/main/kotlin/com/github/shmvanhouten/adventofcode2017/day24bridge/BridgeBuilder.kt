package com.github.shmvanhouten.adventofcode2017.day24bridge

abstract class BridgeBuilder {

    fun buildMostSuitableBridge(bridgeComponents: List<BridgeComponent>): Int {

        val uncompletedBridges = BridgeIterator(buildStartingBridges(bridgeComponents))
        var bestBridge = Bridge(emptyList(), 0)

        while (uncompletedBridges.hasNext()) {
            val (currentBridge, visitedComponentIndexes) = uncompletedBridges.getNext()

            if (currentBridge.openPort == 0) {
                if (isCurrentBridgeBetter(currentBridge, bestBridge)) bestBridge = currentBridge
            } else {
                val newBridges = addMatchingComponentsToEnd(bridgeComponents, visitedComponentIndexes, currentBridge)

                if (newBridges.isEmpty()) {
                    if (isCurrentBridgeBetter(currentBridge, bestBridge)) bestBridge = currentBridge
                } else {
                    uncompletedBridges.addBridges(newBridges)
                }
            }
        }

        return bestBridge.strength
    }

    private fun addMatchingComponentsToEnd(bridgeComponents: List<BridgeComponent>, visitedComponentIndexes: List<Int>, currentBridge: Bridge): List<Pair<Bridge, List<Int>>> {
        return (0.until(bridgeComponents.size))
                .filter { !visitedComponentIndexes.contains(it) }
                .filter { bridgeComponents[it].matchPorts(currentBridge.openPort) }
                .map { currentBridge.addComponent(bridgeComponents[it]) to visitedComponentIndexes.plus(it) }
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

