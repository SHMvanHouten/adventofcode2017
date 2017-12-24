package com.github.shmvanhouten.adventofcode2017.day24bridge

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter
import com.github.shmvanhouten.adventofcode2017.util.splitIntoTwo

class BridgeComponentConverter(private val converter: RawInstructionConverter = RawInstructionConverter()) {

    fun convertListOfComponents(path: String): Iterable<BridgeComponent> {
        return converter.convertIndexedRawInputIntoInstructions(path, this::parseStringToBridgeComponent)
                .asIterable()
    }

    private fun parseStringToBridgeComponent(readline: String, index: Int): BridgeComponent {
        val (firstPort, secondPort) = readline.splitIntoTwo("/")
        return BridgeComponent(index, firstPort.toInt(), secondPort.toInt())
    }


}