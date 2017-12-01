package day01inversecaptcha

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DigitSequenceReviewerAcrossIndexImplTest {
    @Test
    fun `it should review the sequence 1212 to produce a sum of 3`() {
        val reviewer = DigitSequenceReviewerAcrossIndexImpl()

        assertThat(reviewer.createSumOfRepeatedDigits("1212"), equalTo(6))
    }

    @Test
    fun `it should review the sequence 1221 to produce a sum of 0`() {
        val reviewer = DigitSequenceReviewerAcrossIndexImpl()

        assertThat(reviewer.createSumOfRepeatedDigits("1221"), equalTo(0))
    }

    @Test
    fun `it should review the sequence 123425 to produce a sum of 4`() {
        val reviewer = DigitSequenceReviewerAcrossIndexImpl()

        assertThat(reviewer.createSumOfRepeatedDigits("123425"), equalTo(4))
    }

    @Test
    fun `it should review the sequence 123123 to produce a sum of 12`() {
        val reviewer = DigitSequenceReviewerAcrossIndexImpl()

        assertThat(reviewer.createSumOfRepeatedDigits("123123"), equalTo(12))
    }


    @Test
    fun `it should solve the challenge input`() {
        val reviewer = DigitSequenceReviewerAcrossIndexImpl()

        assertThat(reviewer.createSumOfRepeatedDigits(day1ChallengeInput), equalTo(1171))
    }

}