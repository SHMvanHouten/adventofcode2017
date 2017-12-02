package day02corruptionchecksum

class ChecksumCalculator {

    fun calculateChecksum(rawInput: String): Int {
        val rows = rawInput.split("\n")
        return rows.sumBy { getDifferenceBetweenHighestAndLowestValue(it) }
    }

    private fun getDifferenceBetweenHighestAndLowestValue(rawInput: String): Int {
        val rowValues = rawInput
                .split("\t", " ")
                .map { it.toInt() }
                .sorted()

        return rowValues.last() - rowValues.first()
    }
}