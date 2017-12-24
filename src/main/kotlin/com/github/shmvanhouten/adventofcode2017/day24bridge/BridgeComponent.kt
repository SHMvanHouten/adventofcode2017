package com.github.shmvanhouten.adventofcode2017.day24bridge

data class BridgeComponent(val firstPort: Int, val secondPort: Int) {

    fun matchPorts(portToMatchTo: Int): Boolean {
        return firstPort == portToMatchTo || secondPort == portToMatchTo
    }

    fun getOtherPort(port: Int): Int {
        return if(firstPort == port){
            secondPort
        } else {
            firstPort
        }
    }
}
