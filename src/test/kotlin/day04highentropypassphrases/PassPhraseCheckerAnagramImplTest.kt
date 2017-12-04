package day04highentropypassphrases

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class PassPhraseCheckerAnagramImplTest {

    @Test
    fun `it should count 1 valid phrase`() {
        val checker = PassPhraseCheckerAnagramImpl()
        val inputPhrases = """abcde fghij
abcde xyz ecdab"""
        assertThat(checker.countValidPhrases(inputPhrases), equalTo(1))
    }

    @Test
    fun `it should count 251 valid phrases for the challenge input`() {
        val checker = PassPhraseCheckerAnagramImpl()
        assertThat(checker.countValidPhrases(day04ChallengeInput), equalTo(251))
    }


}
