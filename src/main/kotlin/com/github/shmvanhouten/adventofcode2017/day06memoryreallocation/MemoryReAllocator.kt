package com.github.shmvanhouten.adventofcode2017.day06memoryreallocation

class MemoryReAllocator(val floorBuilder: FloorBuilder = FloorBuilder()) {

    fun reallocateMemoryUntilDuplicateSituationOccurs(rawInput: String): Int {
        var currentFloor = floorBuilder.buildFloorFromRawInput(rawInput)
        var floorStates = setOf(currentFloor.getCurrentStateOfFloors())

        var steps = 0
        while (true){
            steps++

            currentFloor = currentFloor.reallocateBiggestBank()
            val currentStateOfFloors = currentFloor.getCurrentStateOfFloors()
            if(floorStates.any{ it == currentStateOfFloors }){
                return steps
            }
            floorStates = floorStates.plus(currentStateOfFloors)
        }
    }
}