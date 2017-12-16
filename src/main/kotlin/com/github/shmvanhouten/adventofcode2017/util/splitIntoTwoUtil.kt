package com.github.shmvanhouten.adventofcode2017.util

fun <E> List<E>.splitIntoTwo(indexToSplitReversedList: Int): Pair<List<E>, List<E>> {
    val firstPart = this.subList(0, indexToSplitReversedList)
    val secondPart = this.subList(indexToSplitReversedList, this.size)
    return Pair(firstPart, secondPart)
}

fun String.splitIntoTwo(delimeter: String): Pair<String, String> {
    val split = this.split(delimeter, limit = 2)
    return Pair(split[0], split[1])
}