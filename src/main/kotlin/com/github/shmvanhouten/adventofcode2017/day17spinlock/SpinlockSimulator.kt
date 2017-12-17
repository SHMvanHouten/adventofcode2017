package com.github.shmvanhouten.adventofcode2017.day17spinlock

class SpinlockSimulator {

    fun getValueAfterLastInserted(amountOfInsertions: Int, amountOfStepsPerInsertion: Int): Int {

        var currentPosition = LinkedBufferPosition(0)

        (1..amountOfInsertions).forEach { value ->
            val amountOfStepsToTake = calculateAmountOfStepsToTake(amountOfStepsPerInsertion, value)
            currentPosition = takeSteps(currentPosition, amountOfStepsToTake)

            val insertedPosition = LinkedBufferPosition(value)
            assignNeighbourPositions(insertedPosition, currentPosition)

            currentPosition = insertedPosition
        }

        return currentPosition.nextPosition.value
    }

    fun getValueAfterZero(amountOfInsertions: Int, amountOfStepsPerInsertion: Int): Int {

        var currentPosition = 0

        // the value 0 is always on position 0
        var valueOfPositionDirectlyAfterZero = 0

        (1..amountOfInsertions).forEach { value ->

            currentPosition = calculatePositionToStepTo(amountOfStepsPerInsertion, value, currentPosition)

            if (currentPosition == 0) {
                valueOfPositionDirectlyAfterZero = value
            }

            currentPosition++
        }

        return valueOfPositionDirectlyAfterZero
    }


    private fun calculatePositionToStepTo(amountOfStepsPerInsertion: Int, bufferSize: Int, currentPosition: Int): Int {
        val newPosition = currentPosition + amountOfStepsPerInsertion % bufferSize
        return if (newPosition < bufferSize) {
            newPosition
        } else {
            newPosition - bufferSize
        }
    }

    private fun assignNeighbourPositions(insertedPosition: LinkedBufferPosition, currentPosition: LinkedBufferPosition) {
        insertedPosition.nextPosition = currentPosition.nextPosition
        currentPosition.nextPosition = insertedPosition
    }

    private tailrec fun takeSteps(currentPosition: LinkedBufferPosition, amountOfStepsToTake: Int): LinkedBufferPosition {
        if (amountOfStepsToTake == 0) return currentPosition
        return takeSteps(currentPosition.nextPosition, amountOfStepsToTake - 1)
    }

    private fun calculateAmountOfStepsToTake(amountOfStepsPerInsertion: Int, bufferSize: Int): Int {

        return amountOfStepsPerInsertion % bufferSize
    }
}