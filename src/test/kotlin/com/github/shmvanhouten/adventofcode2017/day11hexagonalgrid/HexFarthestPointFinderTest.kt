package com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class HexFarthestPointFinderTest {

    @Test
    fun `it should find the farthest point was 2 steps away`() {
        val farthestPointFinder = HexFarthestPointFinder()
        assertThat(farthestPointFinder.findFarthestPointInRoute("ne,se,sw"), equalTo(2))
    }

    @Test
    fun `it should find the farthest point was 3 steps away`() {
        val farthestPointFinder = HexFarthestPointFinder()
        assertThat(farthestPointFinder.findFarthestPointInRoute("se,se,s,sw,n,sw,nw,ne,n"), equalTo(3))
    }

    @Test
    fun `it should solve the challenge input`() {
        val farthestPointFinder = HexFarthestPointFinder()
        assertThat(farthestPointFinder.findFarthestPointInRoute(day11ChallengeInput), equalTo(1465))
    }
}