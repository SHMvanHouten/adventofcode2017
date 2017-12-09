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
                    val streamAfterGarbageDeletion = deleteGarbageFromStream(unAnalyzedStream)
                    val garbage = unAnalyzedStream.substring(1, unAnalyzedStream.length - (streamAfterGarbageDeletion.length) - 1)

                    totalGarbageSize += analyzeGarbage(garbage)

                    unAnalyzedStream = streamAfterGarbageDeletion
                }
            }
        }

        return Pair(totalGroupValue, totalGarbageSize)
    }

    private fun analyzeGarbage(garbage: String): Int {

        var remainingGarbage = garbage
        var amountOfDeletedChars = 0
        var indexOfEscapeCharacter = remainingGarbage.indexOf('!')

        while (indexOfEscapeCharacter != -1) {
            amountOfDeletedChars += indexOfEscapeCharacter
            remainingGarbage = removeEscapeCharactersFromGarbage(remainingGarbage.substring(indexOfEscapeCharacter))
            indexOfEscapeCharacter = remainingGarbage.indexOf('!')
        }

        return amountOfDeletedChars + remainingGarbage.length
    }

    private fun removeEscapeCharactersFromGarbage(remainingGarbageFromEscapeChar: String): String {
        var shouldDeleteNextChar = true
        var index = 1
        var charAtIndex = remainingGarbageFromEscapeChar[index]

        while (charAtIndex == '!') {
            shouldDeleteNextChar = !shouldDeleteNextChar
            index++
            if (index == remainingGarbageFromEscapeChar.length){
                return ""
            }

            charAtIndex = remainingGarbageFromEscapeChar[index]
        }

        return if (shouldDeleteNextChar) {
            remainingGarbageFromEscapeChar.substring(index + 1)
        } else {
            remainingGarbageFromEscapeChar.substring(index)
        }
    }

    private tailrec fun deleteGarbageFromStream(unAnalyzedStream: String): String {
        val regex = Regex("""^(.*?)>""")
        val charactersUpToAndIncludingGarbageEnd = regex.find(unAnalyzedStream)!!
        val streamAfterEndIndicator = unAnalyzedStream.substring(charactersUpToAndIncludingGarbageEnd.range.last + 1)
        if (isEndIndicatorValid(charactersUpToAndIncludingGarbageEnd.value)) {
            return streamAfterEndIndicator
        }
        return deleteGarbageFromStream(streamAfterEndIndicator)
    }

    private fun isEndIndicatorValid(charactersUpToAndIncludingGarbageEnd: String): Boolean {
        val charactersReversed = charactersUpToAndIncludingGarbageEnd.reversed().substring(1)
        var isValidIndicator = true
        charactersReversed.forEach {
            if (it == '!') isValidIndicator = !isValidIndicator
            else return isValidIndicator
        }
        return isValidIndicator
    }

}
