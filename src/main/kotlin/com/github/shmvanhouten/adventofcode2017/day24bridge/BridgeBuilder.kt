package com.github.shmvanhouten.adventofcode2017.day24bridge

class BridgeBuilder {

    fun buildStrongestPossibleBridge(bridgeComponents: List<BridgeComponent>): Int {

        val uncompletedBridges = BridgeIterator(buildStartingBridges(bridgeComponents).toMutableList())
        var biggestBridgeStrength = 0

        while (uncompletedBridges.hasNext()) {
            val (currentBridge, visitedComponentIndexes) = uncompletedBridges.getNext()

            val currentStrength = currentBridge.strength
            if (currentBridge.openPort == 0) {
                if (currentStrength > biggestBridgeStrength) biggestBridgeStrength = currentStrength
            } else {

                val newBridges = (0.until(bridgeComponents.size))
                        .filter { !visitedComponentIndexes.contains(it) }
                        .filter { bridgeComponents[it].matchPorts(currentBridge.openPort) }
                        .map{ currentBridge.addComponent(bridgeComponents[it]) to visitedComponentIndexes.plus(it) }


                if (newBridges.isEmpty()) {
                    if (currentStrength > biggestBridgeStrength) biggestBridgeStrength = currentStrength
                } else {
                    uncompletedBridges.addBridges(newBridges)
                }
            }

        }

        return biggestBridgeStrength
    }

    private fun buildStartingBridges(bridgeComponents: List<BridgeComponent>): List<Pair<Bridge, List<Int>>> {
        return bridgeComponents
                .withIndex()
                .filter { it.value.matchPorts(0) }
                .map({ Bridge(listOf(it.value), it.value.getOtherPort(0)) to listOf(it.index) })

    }
}

