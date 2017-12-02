package day02corruptionchecksum

class ChecksumCalculatorLowHighDifferenceImpl : ChecksumCalculator() {

    override fun calculateRowChecksum(rawInput: String): Int {
        val rowValues = getSortedRowValues(rawInput)
        return rowValues.first() - rowValues.last()
    }

}