package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

import com.github.shmvanhouten.adventofcode2017.util.splitIntoTwo


class DanceMoveConverter {
    fun parseStringToDanceMove(rawDanceMove: String): DanceMove {
        val danceMoveParticulars = rawDanceMove.substring(1)
        return when (rawDanceMove[0]) {
            's' -> buildSpinMove(danceMoveParticulars)
            'x' -> buildExchangeMove(danceMoveParticulars)
            'p' -> buildPartnerMove(danceMoveParticulars)
            else -> error("unknown danceMove character for: $rawDanceMove")
        }
    }

    private fun buildSpinMove(danceMoveParticulars: String): DanceMove {
        return SpinMove(danceMoveParticulars.toInt())
    }

    private fun buildExchangeMove(danceMoveParticulars: String): DanceMove {
        val (firstPosition, secondPosition) = danceMoveParticulars.splitIntoTwo("/")
        return ExchangeMove(firstPosition.toInt(), secondPosition.toInt())
    }

    private fun buildPartnerMove(danceMoveParticulars: String): DanceMove {
        return PartnerMove(danceMoveParticulars[0], danceMoveParticulars[2])
    }
}


