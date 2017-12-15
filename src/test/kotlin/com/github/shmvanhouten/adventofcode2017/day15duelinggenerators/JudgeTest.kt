package com.github.shmvanhouten.adventofcode2017.day15duelinggenerators

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class JudgeTest {

    @Test
    fun `it should count 1 matching pairs for 5 calculations `() {
        val judge = Judge(NumberGenerator(65, 16807), NumberGenerator(8921, 48271))

        assertThat(judge.countAmountOfMatchingPairs(5), equalTo(1))
    }

    @Test
    fun `it should count 588 matching pairs for 40 million calculations `() {
        val judge = Judge(NumberGenerator(65, 16807), NumberGenerator(8921, 48271))

        assertThat(judge.countAmountOfMatchingPairs(40_000_000), equalTo(588))
    }

    @Test
    fun `it should count 577 matching pairs for 40 million calculations for the challenge input`() {
        val judge = Judge(NumberGenerator(618, 16807), NumberGenerator(814, 48271))

        assertThat(judge.countAmountOfMatchingPairs(40_000_000), equalTo(577))
    }




}