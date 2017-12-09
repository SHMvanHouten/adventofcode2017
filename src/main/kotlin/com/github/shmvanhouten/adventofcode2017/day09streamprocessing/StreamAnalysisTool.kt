package com.github.shmvanhouten.adventofcode2017.day09streamprocessing

class StreamAnalysisTool {

    fun deleteGarbageAndFindGroupValue(stream: String): Pair<Int, Int> {
        var totalGroupValue = 0
        var currentGroupValue = 0
        var unAnalyzedStream = stream
        var totalGarbageSize = 0


        while (unAnalyzedStream.isNotEmpty()) {
            val firstRelevantCharacter = unAnalyzedStream[0]
            when (firstRelevantCharacter) {

                ',' -> {
                    unAnalyzedStream = unAnalyzedStream.substring(1)
                }
                '{' -> {
                    currentGroupValue++
                    unAnalyzedStream = unAnalyzedStream.substring(1)
                }
                '}' -> {
                    totalGroupValue += currentGroupValue
                    currentGroupValue--
                    unAnalyzedStream = unAnalyzedStream.substring(1)
                }
                '<' -> {
                    val (streamAfterGarbageRemoval, amountOfUnescapedCharactersRemoved) = deleteGarbageFromStream(unAnalyzedStream)

                    totalGarbageSize += amountOfUnescapedCharactersRemoved

                    unAnalyzedStream = streamAfterGarbageRemoval
                }
            }
        }

        return Pair(totalGroupValue, totalGarbageSize)
    }

    private tailrec fun deleteGarbageFromStream(unAnalyzedStream: String, amountOfDeletedCharacters: Int = 0): Pair<String, Int> {
        val relevantCharacter = Regex("""[!>]""").find(unAnalyzedStream)!!
        val indexOfRelevantCharacter = relevantCharacter.range.first
        val newAmountOfDeletedCharacters = amountOfDeletedCharacters + indexOfRelevantCharacter

        if (relevantCharacter.value == ">") {
            val unAnalyzedStreamAfterGarbageClose = unAnalyzedStream.substring(indexOfRelevantCharacter + 1)
            return Pair(unAnalyzedStreamAfterGarbageClose, newAmountOfDeletedCharacters - 1)
        }

        // relevant character is an escape character (!)
        val streamWithEscapedCharsRemoved = removeEscapeCharactersFromGarbage(unAnalyzedStream.substring(indexOfRelevantCharacter))
        return deleteGarbageFromStream(streamWithEscapedCharsRemoved, newAmountOfDeletedCharacters)
    }

    private tailrec fun removeEscapeCharactersFromGarbage(remainingGarbageFromEscapeChar: String, nextCharIsEscaped: Boolean = false): String {

        val firstChar = remainingGarbageFromEscapeChar.first()
        if(firstChar != '!'){
            return if(nextCharIsEscaped) remainingGarbageFromEscapeChar.substring(1)
            else remainingGarbageFromEscapeChar.substring(0)
        }

        return removeEscapeCharactersFromGarbage(remainingGarbageFromEscapeChar.substring(1), !nextCharIsEscaped)
    }
}
