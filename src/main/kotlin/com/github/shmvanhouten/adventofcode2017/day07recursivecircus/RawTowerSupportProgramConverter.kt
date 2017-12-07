package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.Instruction
import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.RawInstructionConverter

class RawTowerSupportProgramConverter : RawInstructionConverter() {

    fun getListOfTowerSupportPrograms(relativePath: String): List<TowerSupportProgram> {
        return convertRawInputIntoInstructions(relativePath) as List<TowerSupportProgram>
    }

    override fun convertInstruction(readline: String): Instruction {
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