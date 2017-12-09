package com.github.shmvanhouten.adventofcode2017.day09streamprocessing

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class StreamAnalysisToolTest {

    @Test
    fun `it should find 1 group with a score of 1`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{}").first, equalTo(1))
    }

    @Test
    fun `it should find 2 groups with a score of 2`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{},{}").first, equalTo(2))
    }

    @Test
    fun `it should find 2 groups with a score of 3`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{{}}").first, equalTo(3))
    }

    @Test
    fun `it should find 3 groups with a score of 4`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{{}},{}").first, equalTo(4))
    }

    @Test
    fun `it should find 1 group with a score of 1 because the rest is garbage`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{<{}},{>}").first, equalTo(1))
    }

    @Test
    fun `it should find 1 group with a score of 1 because the rest is garbage with an escaped garbage end indicator`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{<{}!>}{>}").first, equalTo(1))
    }

    @Test
    fun `it should find 1 group with a score of 1 because the rest is garbage with a triple escaped garbage end indicator`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{<{}!!!>},{>}").first, equalTo(1))
    }

    @Test
    fun `it should find 1 group with a score of 1 because the rest is garbage with a double escaped garbage end indicator`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{<{}!!!>},{!!>}").first, equalTo(1))
    }

    @Test
    fun `it should give a score of 6`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{{{}}}").first, equalTo(6))
    }

    @Test
    fun `it should give a score of 5`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{{},{}}").first, equalTo(5))
    }

    @Test
    fun `it should give a score of 16`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{{{},{},{{}}}}").first, equalTo(16))
    }

    @Test
    fun `it should give a score of 1`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{<a>,<a>,<a>,<a>}").first, equalTo(1))
    }

    @Test
    fun `it should solve the challenge input`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue(day09Input).first, equalTo(20530))
    }


    @Test
    fun `it should find the amount of unescaped garbage characters to be 5`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{<{}!!!>},{!!>}").second, equalTo(5))
    }

    @Test
    fun `it should find the amount of unescaped garbage characters to be 4`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue("{<a>,<a>,<a>,<a>}").second, equalTo(4))
    }

    @Test
    fun `it should find the amount of unescaped garbage characters in the challenge input to be 9978`() {
        val analysisTool = StreamAnalysisTool()
        assertThat(analysisTool.deleteGarbageAndFindGroupValue(day09Input).second, equalTo(9978))
    }
}

