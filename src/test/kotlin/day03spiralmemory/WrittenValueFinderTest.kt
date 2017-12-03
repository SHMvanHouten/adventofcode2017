package day03spiralmemory

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class WrittenValueFinderTest {

    @Test
    fun `it should find the first value bigger than 26 to be 54`() {
        val valueFinder = WrittenValueFinder()
        assertThat(valueFinder.findFirstWrittenValueLargerThan(26) , equalTo(54))
    }

    @Test
    fun `it should find the first value bigger than 150 to be 304`() {
        val valueFinder = WrittenValueFinder()
        assertThat(valueFinder.findFirstWrittenValueLargerThan(150) , equalTo(304))
    }

    @Test
    fun `it should find the first value bigger than 350 to be 351`() {
        val valueFinder = WrittenValueFinder()
        assertThat(valueFinder.findFirstWrittenValueLargerThan(350) , equalTo(351))
    }

    @Test
    fun `it should solve the challenge input`() {
        val valueFinder = WrittenValueFinder()
        assertThat(valueFinder.findFirstWrittenValueLargerThan(312051), equalTo(312453))
    }
}