package com.github.shmvanhouten.adventofcode2017.day15duelinggenerators

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class JudgeTest {

    @Test
    fun `it should count 1 matching pairs for 5 calculations `() {
        val judge = Judge(BasicNumberGenerator(65, 16807), BasicNumberGenerator(8921, 48271))

        assertThat(judge.countAmountOfMatchingPairs(5), equalTo(1))
    }

    @Test
    fun `it should count 588 matching pairs for 40 million calculations `() {
        val judge = Judge(BasicNumberGenerator(65, 16807), BasicNumberGenerator(8921, 48271))

        assertThat(judge.countAmountOfMatchingPairs(40_000_000), equalTo(588))
    }

    @Test
    fun `it should count 577 matching pairs for 40 million calculations for the challenge input`() {
        val judge = Judge(BasicNumberGenerator(618, 16807), BasicNumberGenerator(814, 48271))

        assertThat(judge.countAmountOfMatchingPairs(40_000_000), equalTo(577))
    }

    @Test
    fun `it should count 309 matching pairs for 5 million calculations with picky generators `() {
        val judge = Judge(PickyNumberGenerator(65, 16807, 4), PickyNumberGenerator(8921, 48271, 8))

        assertThat(judge.countAmountOfMatchingPairs(5_000_000), equalTo(309))
    }

    @Test
    fun `it should count 316 matching pairs for 5 million calculations with picky generators for the challenge input`() {
        val judge = Judge(PickyNumberGenerator(618, 16807, 4), PickyNumberGenerator(814, 48271, 8))

        assertThat(judge.countAmountOfMatchingPairs(5_000_000), equalTo(316))
    }




}