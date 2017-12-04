package day04highentropypassphrases

class PassPhraseCheckerAnagramImpl : PassPhraseChecker() {

    override fun checkIfItIsAValidPhrase(phrase: String): Boolean {
        val wordsInPhrase = phrase.split(" ")
        return !wordsInPhrase.any { word -> wordsInPhrase.count { it.toSortedSet() == word.toSortedSet() } >= 2 }
    }
}