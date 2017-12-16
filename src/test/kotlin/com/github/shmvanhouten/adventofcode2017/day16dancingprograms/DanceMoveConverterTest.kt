package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DanceMoveConverterTest {

    @Test
    fun `it should convert the raw instruction into a spin move`() {
        val converter = DanceMoveConverter()
        assertThat(converter.parseStringToDanceMove("s1") is SpinMove, equalTo(true))
    }

    @Test
    fun `it should convert the raw instruction into an exchange move`() {
        val converter = DanceMoveConverter()
        assertThat(converter.parseStringToDanceMove("x3/4") is ExchangeMove, equalTo(true))
    }

    @Test
    fun `it should convert the raw instruction into a partner move`() {
        val converter = DanceMoveConverter()
        assertThat(converter.parseStringToDanceMove("pe/b") is PartnerMove, equalTo(true))
    }



}