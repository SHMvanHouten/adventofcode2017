package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

class DanceSimulator(private val danceMoveConverter: DanceMoveConverter = DanceMoveConverter()) {

    fun getOrderOfProgramsAfterDance(rawInput: String, amountOfDancePrograms: Int): String {
        val dancePrograms = ('a'..'z').toList().subList(0, amountOfDancePrograms).map { DanceProgram(it) }

        val danceProgramsAfterDance = rawInput.split(',')
                .fold(dancePrograms, { dancingPrograms, rawDanceMove -> performMove(rawDanceMove, dancingPrograms) })

        return danceProgramsAfterDance.joinToString("")
    }

    private fun performMove(rawDanceMove: String, dancingPrograms: List<DanceProgram>): List<DanceProgram> {
        val danceMove = danceMoveConverter.parseStringToDanceMove(rawDanceMove)
        val move = danceMove.getMove()
        return move(dancingPrograms)
    }
}