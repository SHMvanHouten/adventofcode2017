package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

class DanceSimulator(private val converter: DanceMoveConverter = DanceMoveConverter(), private val danceMoveCondenser: DanceMoveCondenser = DanceMoveCondenser()) {


    fun getOrderOfProgramsAfterXDancesBruteForce(rawInput: String, amountOfDancePrograms: Int, amountOfDances: Int): String {
        var dancePrograms = getListOfDancePrograms(amountOfDancePrograms)

        val danceMoves = rawInput.split(',')
                .map { converter.parseStringToDanceMove(it) }
        val condensedDanceMoves = danceMoveCondenser.splitAndCondenseDanceMoves(danceMoves, dancePrograms)

        (0.until(amountOfDances)).forEach {
            dancePrograms = performDanceMoves(dancePrograms, condensedDanceMoves)
        }

        return dancePrograms.joinToString("")
    }

    fun getOrderOfProgramsAfterXDances(rawInput: String, amountOfDancePrograms: Int, amountOfDances: Int): String {
        var dancePrograms = getListOfDancePrograms(amountOfDancePrograms)

        val danceMoves = rawInput.split(',')
                .map { converter.parseStringToDanceMove(it) }
        val condensedDanceMoves = danceMoveCondenser.splitAndCondenseDanceMoves(danceMoves, dancePrograms)

        val amountOfDancesToReturnToOriginalState = findAmountOfDancesItTakesToGetBackToOriginalState(dancePrograms, condensedDanceMoves)

        (0.until(amountOfDances % amountOfDancesToReturnToOriginalState)).forEach {
            dancePrograms = performDanceMoves(dancePrograms, danceMoves)
        }

        return dancePrograms.joinToString("")
    }


    fun getOrderOfProgramsAfterDance(rawInput: String, amountOfDancePrograms: Int): String {
        val dancePrograms = getListOfDancePrograms(amountOfDancePrograms)

        val danceProgramsAfterDance = rawInput.split(',')
                .fold(dancePrograms, { dancingPrograms, rawDanceMove -> performMove(rawDanceMove, dancingPrograms) })

        return danceProgramsAfterDance.joinToString("")
    }

    private fun findAmountOfDancesItTakesToGetBackToOriginalState(originalDanceProgram: List<Char>, danceMoves: List<DanceMove>): Int {

        var dancingProgram = performDanceMoves(originalDanceProgram, danceMoves)
        var amountOfDances = 1

        while (dancingProgram != originalDanceProgram) {
            dancingProgram = performDanceMoves(dancingProgram, danceMoves)
            amountOfDances++
        }

        println(amountOfDances)
        return amountOfDances
    }


    private fun getListOfDancePrograms(amountOfDancePrograms: Int): List<Char> {
        return ('a'..'z').toList().subList(0, amountOfDancePrograms).map { it }
    }

    private fun performMove(rawDanceMove: String, dancingPrograms: List<Char>): List<Char> {
        val danceMove = converter.parseStringToDanceMove(rawDanceMove)
        val move = danceMove.getMove()
        return move(dancingPrograms)
    }

    private fun performDanceMoves(dancePrograms: List<Char>, danceMoves: List<DanceMove>): List<Char> {
        return danceMoves.fold(dancePrograms, { dancingPrograms, danceMove -> danceMove.getMove()(dancingPrograms) })
    }
}
