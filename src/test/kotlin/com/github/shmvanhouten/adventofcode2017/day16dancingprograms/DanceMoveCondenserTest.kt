package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DanceMoveCondenserTest {

    @Test
    fun `it should condense the moves into one`() {
        val condenser = DanceMoveCondenser()
        val danceMoves = condenser.splitAndCondenseDanceMoves(listOf(SpinMove(1), ExchangeMove(3, 4)), listOf('a', 'b', 'c', 'd', 'e'))
        assertThat(danceMoves[0].getMove()(listOf('a', 'b', 'c', 'd', 'e')).joinToString(""), equalTo("eabdc"))
    }

    @Test
    fun `it should move all the exchange moves to the start and condense them`() {
        val condenser = DanceMoveCondenser()
        val danceMoves = condenser.splitAndCondenseDanceMoves(listOf(
                ExchangeMove(0,3),
                PartnerMove('a', 'c'),
                ExchangeMove(1,4),
                ExchangeMove(0,2),
                PartnerMove('d', 'e'),
                PartnerMove('a', 'e')),
                listOf('a', 'b', 'c', 'd', 'e'))
        val danceGroupAfterMoves = danceMoves
                .fold(listOf('a', 'b', 'c', 'd', 'e'), { dancingGroup, danceMove -> danceMove.getMove()(dancingGroup) })
                .joinToString("")
        assertThat(danceGroupAfterMoves, equalTo("edacb"))
    }

    @Test
    fun `it should move all the spin and exchange moves to the start and condense them`() {
        val condenser = DanceMoveCondenser()
        val danceMoves = condenser.splitAndCondenseDanceMoves(listOf(
                ExchangeMove(0,3),
                PartnerMove('a', 'c'),
                ExchangeMove(1,4),
                ExchangeMove(0,2),
                PartnerMove('d', 'e'),
                PartnerMove('a', 'e'),
                SpinMove(1)),
                listOf('a', 'b', 'c', 'd', 'e'))
        val danceGroupAfterMoves = danceMoves
                .fold(listOf('a', 'b', 'c', 'd', 'e'), { dancingGroup, danceMove -> danceMove.getMove()(dancingGroup) })
                .joinToString("")
        assertThat(danceGroupAfterMoves, equalTo("bedac"))
    }

    @Test
    fun `it should condense the Partner moves`() {
        val condenser = DanceMoveCondenser()
        val danceMoves = condenser.splitAndCondenseDanceMoves(listOf(
                PartnerMove('b', 'd'),
                PartnerMove('a', 'b'),
                PartnerMove('c', 'd')),
                listOf('a', 'b', 'c', 'd', 'e'))
        val danceGroupAfterMoves = danceMoves
                .fold(listOf('a', 'b', 'c', 'd', 'e'), { dancingGroup, danceMove -> danceMove.getMove()(dancingGroup) })
                .joinToString("")
        assertThat(danceGroupAfterMoves, equalTo("bcdae"))
    }
}