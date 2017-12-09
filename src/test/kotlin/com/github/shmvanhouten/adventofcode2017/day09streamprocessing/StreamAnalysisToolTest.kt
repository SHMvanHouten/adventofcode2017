package com.github.shmvanhouten.adventofcode2017.day09streamprocessing

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test

class StreamAnalysisToolTest {

    @Test
    fun `it should find 1 group with a score of 1`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{}"), equalTo(1))
    }

    @Test
    fun `it should find 2 groups with a score of 2`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{},{}"), equalTo(2))
    }

    @Test
    fun `it should find 2 groups with a score of 3`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{{}}"), equalTo(3))
    }

    @Test
    fun `it should find 3 groups with a score of 4`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{{}},{}"), equalTo(4))
    }

    @Test
    fun `it should find 1 group with a score of 1 because the rest is garbage`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{<{}},{>}"), equalTo(1))
    }

    @Test
    fun `it should find 1 group with a score of 1 because the rest is garbage with an escaped garbage end indicator`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{<{}!>}{>}"), equalTo(1))
    }

    @Test
    fun `it should find 1 group with a score of 1 because the rest is garbage with a triple escaped garbage end indicator`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{<{}!!!>},{>}"), equalTo(1))
    }

    @Test
    fun `it should find 1 group with a score of 1 because the rest is garbage with a double escaped garbage end indicator`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{<{}!!!>},{!!>}"), equalTo(1))
    }

    @Test
    fun `it should give a score of 6`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{{{}}}"), equalTo(6))
    }

    @Test
    fun `it should give a score of 5`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{{},{}}"), equalTo(5))
    }

    @Test
    fun `it should give a score of 16`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{{{},{},{{}}}}"), equalTo(16))
    }

    @Test
    fun `it should give a score of 1`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream("{<a>,<a>,<a>,<a>}"), equalTo(1))
    }



    @Test
    fun `it should solve the challenge input`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.findGroupValueInStream(day09Input), equalTo(1))
    }


}

