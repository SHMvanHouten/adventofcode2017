package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

import com.github.shmvanhouten.adventofcode2017.util.splitIntoTwo


interface DanceMove{

    fun getMove(): (List<DanceProgram>) -> List<DanceProgram>
}

data class SpinMove(private val size: Int): DanceMove{

    override fun getMove(): (List<DanceProgram>) -> List<DanceProgram> {
        return this::spin
    }

    private fun spin(dancePrograms: List<DanceProgram>): List<DanceProgram>{
        val (firstPrograms, secondPrograms) = dancePrograms.splitIntoTwo(dancePrograms.size - size)
        return secondPrograms + firstPrograms
    }

}

data class ExchangeMove(val firstPosition: Int, val secondPosition: Int): DanceMove {

    override fun getMove(): (List<DanceProgram>) -> List<DanceProgram> {
        return this::exchange
    }

    private fun exchange(dancePrograms: List<DanceProgram>): List<DanceProgram> {
        val mutableDancePrograms = dancePrograms.toMutableList()

        val firstProgram = mutableDancePrograms[firstPosition]
        val secondProgram = mutableDancePrograms[secondPosition]

        mutableDancePrograms[firstPosition] = secondProgram
        mutableDancePrograms[secondPosition] = firstProgram

        return mutableDancePrograms
    }
}

data class PartnerMove(private val firstPartner: DanceProgram, private val secondPartner: DanceProgram): DanceMove {

    override fun getMove(): (List<DanceProgram>) -> List<DanceProgram> {
        return this::partner
    }

    private fun partner(dancePrograms: List<DanceProgram>): List<DanceProgram> {
        val mutableDancePrograms = dancePrograms.toMutableList()

        val positionOfFirstProgram = dancePrograms.indexOf(firstPartner)
        val positionOfSecondProgram = dancePrograms.indexOf(secondPartner)

        mutableDancePrograms[positionOfFirstProgram] = secondPartner
        mutableDancePrograms[positionOfSecondProgram] = firstPartner

        return mutableDancePrograms
    }
}


