package com.github.shmvanhouten.adventofcode2017.day15duelinggenerators

class PickyNumberGenerator(startingValue: Long, factor: Long, private val criteria: Int) : NumberGenerator(startingValue, factor) {

    override fun getNext(): Long {
        var nextValue = calculateNextValue()
        while (nextValue % criteria != 0L){
            nextValue = calculateNextValue()
        }
        return nextValue
    }
}