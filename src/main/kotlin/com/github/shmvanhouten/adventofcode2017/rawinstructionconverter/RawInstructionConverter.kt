package com.github.shmvanhouten.adventofcode2017.rawinstructionconverter

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

abstract class RawInstructionConverter {

    fun convertRawInputIntoInstructions(relativePath: String): List<Instruction> {
        val listOfInstructions = mutableListOf<Instruction>()

        val absolutePath = File("").absolutePath
        val file = File(absolutePath.plus(relativePath))
        try {
            BufferedReader(FileReader(file)).use {
                var readline = it.readLine()

                while (readline != null) {
                    val instruction = convertInstruction(readline)
                    listOfInstructions.add(instruction)
                    readline = it.readLine()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listOfInstructions
    }

    abstract fun convertInstruction(readline: String): Instruction
}