package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

import com.github.shmvanhouten.adventofcode2017.util.splitIntoTwo


interface DanceMove {

    fun getMove(): (List<Char>) -> List<Char>
}

data class SpinMove(private val size: Int) : DanceMove {

    override fun getMove(): (List<Char>) -> List<Char> {
        return this::spin
    }

    private fun spin(dancePrograms: List<Char>): List<Char> {
        val (firstPrograms, secondPrograms) = dancePrograms.splitIntoTwo(dancePrograms.size - size)
        return secondPrograms + firstPrograms
    }

}

data class ExchangeMove(private val firstPosition: Int, private val secondPosition: Int) : DanceMove {

    override fun getMove(): (List<Char>) -> List<Char> {
        return this::exchange
    }

    private fun exchange(dancePrograms: List<Char>): List<Char> {
        val mutableDancePrograms = dancePrograms.toMutableList()

        val firstProgram = mutableDancePrograms[firstPosition]
        val secondProgram = mutableDancePrograms[secondPosition]

        mutableDancePrograms[firstPosition] = secondProgram
        mutableDancePrograms[secondPosition] = firstProgram

        return mutableDancePrograms
    }
}

data class PartnerMove(private val firstPartner: Char, private val secondPartner: Char) : DanceMove {

    override fun getMove(): (List<Char>) -> List<Char> {
        return this::partner
    }

    private fun partner(dancePrograms: List<Char>): List<Char> {
        val mutableDancePrograms = dancePrograms.toMutableList()

        val positionOfFirstProgram = dancePrograms.indexOf(firstPartner)
        val positionOfSecondProgram = dancePrograms.indexOf(secondPartner)

        mutableDancePrograms[positionOfFirstProgram] = secondPartner
        mutableDancePrograms[positionOfSecondProgram] = firstPartner

        return mutableDancePrograms
    }
}

data class CondensedDanceMove(private val move: (List<Char>, List<Int>) -> List<Char>, private val newPositionsOfPrograms: List<Int>) : DanceMove {

    override fun getMove(): (List<Char>) -> List<Char> {
        return this::moveCharactersToNewPositions
    }

    private fun moveCharactersToNewPositions(dancePrograms: List<Char>): List<Char> {
        return move(dancePrograms, newPositionsOfPrograms)
    }
}

data class CondensedPartnerMove(private val move: (List<Char>, Map<Char, Char>) -> List<Char>, private val linkedPrograms: Map<Char, Char>) : DanceMove {

    override fun getMove(): (List<Char>) -> List<Char> {
        return this::moveCharactersToNewPositions
    }

    private fun moveCharactersToNewPositions(dancePrograms: List<Char>): List<Char> {
        return move(dancePrograms, linkedPrograms)
    }
}