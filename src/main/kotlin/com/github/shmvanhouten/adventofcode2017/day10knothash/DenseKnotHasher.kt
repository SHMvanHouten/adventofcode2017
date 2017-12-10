package com.github.shmvanhouten.adventofcode2017.day10knothash

class DenseKnotHasher: KnotHasher() {

    fun performDenseHash(inputLengths: String, rangeSize: Int = 256, timesToHash: Int = 64): String {
        val listOfAsciiToUseToHash = inputLengths.map { it.toInt() }
                .plus(listOf(17, 31, 73, 47, 23))
        val basicHashedList = createHash(rangeSize, listOfAsciiToUseToHash, timesToHash)

        val denseHashBuilder = StringBuilder()

        (0.until(rangeSize).step(16)).forEach { index ->
            val block = basicHashedList.subList(index, index + 16)
            val condensedHexValue = condenseBlock(block)
                    .toHexString()

            denseHashBuilder.append(condensedHexValue)
        }
        
        return denseHashBuilder.toString()
    }

    private fun condenseBlock(block: List<Int>): Int {
        return block.reduce { acc, i -> acc xor i }
    }
}

private fun Int.toHexString(): String {
    val hexedInt = Integer.toHexString(this)
    if(hexedInt.length == 1){
        return "0" + hexedInt
    }
    return hexedInt
}
