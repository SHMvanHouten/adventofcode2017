package com.github.shmvanhouten.adventofcode2017.day15duelinggenerators

class BasicNumberGenerator(startingValue: Long, factor: Long): NumberGenerator(startingValue, factor) {

    override fun getNext(): Long {
        return calculateNextValue()
    }
}