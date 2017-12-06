package com.github.shmvanhouten.adventofcode2017.day06memoryreallocation

class MemoryReAllocator(val floorBuilder: FloorBuilder = FloorBuilder()) {

    fun reallocateMemoryUntilDuplicateSituationOccurs(rawInput: String): Pair<Int, FloorOfMemoryBanks> {
        var currentFloor = floorBuilder.buildFloorFromRawInput(rawInput)
        var floorStates = setOf(currentFloor.getCurrentStateOfFloors())

        var steps = 0
        while (true) {
            steps++

            currentFloor = currentFloor.reallocateBiggestBank()
            val currentStateOfFloors = currentFloor.getCurrentStateOfFloors()
            if (floorStates.any { it == currentStateOfFloors }) {
                return Pair(steps, currentFloor)
            }
            floorStates = floorStates.plus(currentStateOfFloors)
        }
    }

    fun reallocateMemoryUntilDuplicateSituationOccursASecondTimeAfterTheFirst(rawInput: String): Int {
        val (_, floor) = reallocateMemoryUntilDuplicateSituationOccurs(rawInput)
        val floorStateToCompareAgainst = floor.getCurrentStateOfFloors()

        var currentFloor = floor

        var steps = 0
        while (true) {
            steps++

            currentFloor = currentFloor.reallocateBiggestBank()
            val currentStateOfFloors = currentFloor.getCurrentStateOfFloors()

            if(currentStateOfFloors == floorStateToCompareAgainst){
                return steps
            }
        }
    }
}