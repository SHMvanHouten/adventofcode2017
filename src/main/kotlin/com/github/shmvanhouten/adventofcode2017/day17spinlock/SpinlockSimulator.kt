package com.github.shmvanhouten.adventofcode2017.day17spinlock

class SpinlockSimulator {
    fun getValueAfterLastInserted(amountOfInsertions: Int, amountOfStepsPerInsertion: Int): Int {

        var currentPosition = LinkedBufferPosition(0)

        var bufferSize = 1

        while (bufferSize <= amountOfInsertions) {
            val amountOfStepsToTake = calculatePositionToStepTo(amountOfStepsPerInsertion, bufferSize)
            currentPosition = takeSteps(currentPosition, amountOfStepsToTake)

            val insertedPosition = LinkedBufferPosition(bufferSize)
            assignNeighbourPositions(insertedPosition, currentPosition)

            currentPosition = insertedPosition
            bufferSize++
        }
        return currentPosition.nextPosition.value
    }

    private fun assignNeighbourPositions(insertedPosition: LinkedBufferPosition, currentPosition: LinkedBufferPosition) {
        insertedPosition.nextPosition = currentPosition.nextPosition
        currentPosition.nextPosition = insertedPosition
    }

    private tailrec fun takeSteps(currentPosition: LinkedBufferPosition, amountOfStepsToTake: Int): LinkedBufferPosition {
        if (amountOfStepsToTake == 0) return currentPosition
        return takeSteps(currentPosition.nextPosition, amountOfStepsToTake - 1)
    }

    private fun calculatePositionToStepTo(amountOfStepsPerInsertion: Int, bufferSize: Int): Int {

        return amountOfStepsPerInsertion % bufferSize
    }
}