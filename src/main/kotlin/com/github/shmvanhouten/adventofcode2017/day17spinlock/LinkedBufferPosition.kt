package com.github.shmvanhouten.adventofcode2017.day17spinlock

data class LinkedBufferPosition(val value: Int) {
    var nextPosition: LinkedBufferPosition = this
}