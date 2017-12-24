package com.github.shmvanhouten.adventofcode2017.day24bridge

class LongestBridgeBuilder: BridgeBuilder() {

    override fun isCurrentBridgeBetter(currentBridge: Bridge, bestBridge: Bridge): Boolean {
        val currentLength = currentBridge.length
        val bestBridgeLength = bestBridge.length
        return currentLength > bestBridgeLength || (currentLength == bestBridgeLength && currentBridge.strength > bestBridge.strength)
    }


}

