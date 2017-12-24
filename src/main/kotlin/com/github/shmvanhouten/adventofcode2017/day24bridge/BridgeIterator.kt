package com.github.shmvanhouten.adventofcode2017.day24bridge

class BridgeIterator(private val bridges: MutableList<Bridge>) {

    private var index = 0

    fun getNext(): Bridge {
        val currentBridge = bridges[index]
        index++
        return currentBridge
    }

    fun addBridges(bridges: List<Bridge>){
        this.bridges.addAll(bridges)
    }

    fun hasNext(): Boolean = index < bridges.size
}