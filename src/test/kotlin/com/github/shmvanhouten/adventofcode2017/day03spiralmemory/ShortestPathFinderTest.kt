package com.github.shmvanhouten.adventofcode2017.day03spiralmemory

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ShortestPathFinderTest {

    @Test
    fun `it should find that the shortest path to 12 is 3 steps`() {
        val pathFinder = ShortestPathFinder()
        assertThat(pathFinder.findPathTo(12), equalTo(3))
    }

    @Test
    fun `it should find that the shortest path to 1 is 0 steps`() {
        val pathFinder = ShortestPathFinder()
        assertThat(pathFinder.findPathTo(1), equalTo(0))
    }

    @Test
    fun `it should find that the shortest path to 23 is 2 steps`() {
        val pathFinder = ShortestPathFinder()
        assertThat(pathFinder.findPathTo(23), equalTo(2))
    }

    @Test
    fun `it should find that the shortest path to 1024 is 31 steps`() {
        val pathFinder = ShortestPathFinder()
        assertThat(pathFinder.findPathTo(1024), equalTo(31))
    }

    @Test
    fun `it should solve the challenge input`() {
        val pathFinder = ShortestPathFinder()
        assertThat(pathFinder.findPathTo(312051), equalTo(430))
    }
}