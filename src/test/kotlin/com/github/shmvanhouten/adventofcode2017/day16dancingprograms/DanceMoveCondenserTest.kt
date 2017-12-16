package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DanceMoveCondenserTest {

    @Test
    fun `it should condense the moves into one`() {
        val condenser = DanceMoveCondenser()
        val danceMove = condenser.condenseDanceMoves(listOf(SpinMove(1), ExchangeMove(3, 4)), listOf('a', 'b', 'c', 'd', 'e'))
        assertThat(danceMove.getMove()(listOf('a', 'b', 'c', 'd', 'e')).joinToString(""), equalTo("eabdc"))
    }
}