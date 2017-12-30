package com.github.shmvanhouten.adventofcode2017.day24bridge

class BridgeIterable(private val bridges: MutableList<Bridge>): Iterable<Bridge> {

    fun addBridges(bridges: List<Bridge>){
        this.bridges.addAll(bridges)
    }

    override fun iterator(): Iterator<Bridge> = BridgeIterator()

    private inner class BridgeIterator: Iterator<Bridge> {
        private var index = 0

        override fun hasNext(): Boolean = index < bridges.size

        override fun next(): Bridge {
            val currentBridge = bridges[index]
            index++
            return currentBridge
        }
    }
}