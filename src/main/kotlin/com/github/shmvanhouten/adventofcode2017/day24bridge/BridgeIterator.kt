package com.github.shmvanhouten.adventofcode2017.day24bridge

class BridgeIterator(private val bridges: MutableList<Pair<Bridge, List<Int>>>) {

    private var index = 0

    fun getNext(): Pair<Bridge, List<Int>> {
        val bridgeEntry = bridges[index]
        index++
        return bridgeEntry
    }

    fun addBridges(bridgeEntries: List<Pair<Bridge, List<Int>>>){
        bridges.addAll(bridgeEntries)
        if(index % 10000 == 0) println(index)
    }

    fun hasNext(): Boolean {
        return index < bridges.size
    }
}