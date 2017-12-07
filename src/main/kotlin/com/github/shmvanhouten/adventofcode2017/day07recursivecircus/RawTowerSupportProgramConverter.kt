package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.RawInstructionConverter

class RawTowerSupportProgramConverter(val rawInstructionConverter: RawInstructionConverter = RawInstructionConverter()) {

    fun getListOfTowerSupportPrograms(relativePath: String): List<TowerSupportProgram> {
        return rawInstructionConverter.convertRawInputIntoInstructions(relativePath, this::convertInstruction)
    }

    private fun convertInstruction(readline: String): TowerSupportProgram {
        val name = readline.substring(0, readline.indexOf(' '))
        val weight = readline.substring(readline.indexOf('(') + 1, readline.indexOf(')')).toInt()
        var listOfTowersItSupports: List<String>? = null
        if (readline.contains("->")) {
            listOfTowersItSupports = readline
                    .substring(readline.indexOf("-> ") + 3)
                    .split(", ")
        }
        return TowerSupportProgram(name, weight, listOfTowersItSupports)
    }
}