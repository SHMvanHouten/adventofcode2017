package com.github.shmvanhouten.adventofcode2017.day09streamprocessing

class StreamAnalysisTool {

    fun findGroupValueInStream(stream: String): Int {
        var totalGroupValue = 0
        var currentGroupValue = 0
        var unAnalyzedStream = stream


        while (unAnalyzedStream.isNotEmpty()){
            val firstRelevantCharacter = unAnalyzedStream[0]
            when(firstRelevantCharacter){
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
                    unAnalyzedStream = unAnalyzedStream.substring( 1)
                }
                '<' -> {
                    unAnalyzedStream = deleteGarbageFromStream(unAnalyzedStream)
                }
            }
        }

        return totalGroupValue
    }

    private tailrec fun deleteGarbageFromStream(unAnalyzedStream: String): String {
        val regex = Regex("""^(.*?)>""")
        val charactersUpToAndIncludingGarbageEnd = regex.find(unAnalyzedStream)!!
        val streamAfterEndIndicator = unAnalyzedStream.substring(charactersUpToAndIncludingGarbageEnd.range.last + 1)
        if(isEndIndicatorValid(charactersUpToAndIncludingGarbageEnd.value)){
            return streamAfterEndIndicator
        }
        return deleteGarbageFromStream(streamAfterEndIndicator)
    }

    private fun isEndIndicatorValid(charactersUpToAndIncludingGarbageEnd: String): Boolean {
        val charactersReversed = charactersUpToAndIncludingGarbageEnd.reversed().substring(1)
        var isValidIndicator = true
        charactersReversed.forEach {
            if(it == '!') isValidIndicator = !isValidIndicator
            else return isValidIndicator
        }
        return isValidIndicator
    }

}

fun main(args: Array<String>) {
    val regex = Regex("""^(.*?)>""")
    val bla = "324324324324!!!>"
    val find = regex.find(bla)!!
    println(find.value)
    println(find.range)

}
