package com.github.shmvanhouten.adventofcode2017.day10knothash

open class KnotHasher {

    fun multiplyFirstTwoNumbersInHash(rangeSize: Int, inputLengths: Set<Int>): Int {
        val hash = createHash(rangeSize, inputLengths)
        return hash.first() * hash.second()
    }

    fun createHash(rangeSize: Int, inputLengths: Iterable<Int>, timesToHash: Int = 1): List<Int> {

        var currentPosition = 0
        var skipSize = 0
        var listToHash = (0.until(rangeSize)).toList()

        (0.until(timesToHash)).forEach {
            inputLengths.forEach { inputLength ->

                listToHash = performHashing(listToHash, currentPosition, inputLength)

                currentPosition = (currentPosition + skipSize + inputLength) % listToHash.size
                skipSize++
            }
        }
        return listToHash
    }

    private fun performHashing(listToHash: List<Int>, currentPosition: Int, inputLength: Int): List<Int> {

        val endIndexForReversedList = currentPosition + inputLength
        return if (endIndexForReversedList <= listToHash.size) {
            val (unchangedStart, sublistToReverse, unchangedEnd) = listToHash
                    .splitThreeWays(currentPosition, endIndexForReversedList)
            unchangedStart + sublistToReverse.reversed() + unchangedEnd
        } else {
            handleSituationIfSublistMovesPastEndIndex(endIndexForReversedList, listToHash, currentPosition)
        }
    }

    private fun handleSituationIfSublistMovesPastEndIndex(endIndexForReversedList: Int, listToHash: List<Int>, currentPosition: Int): List<Int> {
        val listSize = listToHash.size

        val endIndexForSecondPartOfReversedSublist = endIndexForReversedList % listSize

        val (secondPartToReverse, unchanged, firstPartToReverse) = listToHash
                .splitThreeWays(endIndexForSecondPartOfReversedSublist, currentPosition)

        val reversedSublist = (firstPartToReverse + secondPartToReverse).reversed()

        val indexToSplitReversedList = listSize - currentPosition
        val (newEndOfList, newStartOfList) = reversedSublist
                .splitIntoTwo(indexToSplitReversedList)

        return newStartOfList + unchanged + newEndOfList
    }

}

private fun <E> List<E>.splitIntoTwo(indexToSplitReversedList: Int): Pair<List<E>, List<E>> {
    val firstPart = this.subList(0, indexToSplitReversedList)
    val secondPart = this.subList(indexToSplitReversedList, this.size)
    return Pair(firstPart, secondPart)
}

private fun <E> List<E>.splitThreeWays(firstSplitPoint: Int, secondSplitPoint: Int): Triple<List<E>, List<E>, List<E>> {
    val firstPart = this.subList(0, firstSplitPoint)
    val secondPart = this.subList(firstSplitPoint, secondSplitPoint)
    val thirdPart = this.subList(secondSplitPoint, this.size)
    return Triple(firstPart, secondPart, thirdPart)
}


private fun <E> List<E>.second(): E {
    return this[1]
}
