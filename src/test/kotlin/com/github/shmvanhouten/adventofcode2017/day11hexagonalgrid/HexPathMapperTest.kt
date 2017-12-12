package com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class HexPathMapperTest {

    @Test
    fun `it should go northeast from 0,0 to 1,-1`() {
        val mapper = HexPathMapper()
        assertThat(mapper.takePath("ne"), equalTo(HexCoordinate(1,-1)))
    }

    @Test
    fun `it should go southeast from 0,0 to 1,0`() {
        val mapper = HexPathMapper()
        assertThat(mapper.takePath("se"), equalTo(HexCoordinate(1,0)))
    }

    @Test
    fun `it should go se, se, s from 0,0 to 2,2`() {
        val mapper = HexPathMapper()
        assertThat(mapper.takePath("se,se,s"), equalTo(HexCoordinate(2,2)))
    }

    @Test
    fun `it should go se, se, s, sw from 0,0 to 1,2`() {
        val mapper = HexPathMapper()
        assertThat(mapper.takePath("se,se,s,sw"), equalTo(HexCoordinate(1,2)))
    }

    @Test
    fun `it should go se, se, s, sw, n, sw, nw, ne, n from 0,0 to 0,0`() {
        val mapper = HexPathMapper()
        assertThat(mapper.takePath("se,se,s,sw,n,sw,nw,ne,n"), equalTo(HexCoordinate(0,0)))
    }








}