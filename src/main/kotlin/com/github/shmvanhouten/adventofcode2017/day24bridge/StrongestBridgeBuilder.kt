package com.github.shmvanhouten.adventofcode2017.day24bridge

class StrongestBridgeBuilder : BridgeBuilder() {

    override fun isCurrentBridgeBetter(currentBridge: Bridge, bestBridge: Bridge) =
            currentBridge.strength > bestBridge.strength
}