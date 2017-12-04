package day04highentropypassphrases

class PassPhraseCheckerIdenticalImpl : PassPhraseChecker() {

    override fun checkIfItIsAValidPhrase(phrase: String): Boolean {
        val wordsInPhrase = phrase.split(" ")
        return !wordsInPhrase.any { word -> wordsInPhrase.count { it == word } >= 2 }
    }
}