package com.github.shmvanhouten.adventofcode2017.day15duelinggenerators

class NumberGenerator(private val startingValue: Long, private val factor: Long) {

    val STANDARD_DEVISOR = 2147483647
    var currentValue = startingValue

    fun getNext(): Long {
        currentValue = (currentValue * factor) % STANDARD_DEVISOR
        return currentValue
    }
}