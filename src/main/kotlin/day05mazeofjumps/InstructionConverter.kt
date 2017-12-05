package day05mazeofjumps

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class InstructionConverter {

    fun convertRawInputIntoInstructions(relativePath: String): List<JumpInstruction> {
        val listOfJumpInstructions = mutableListOf<JumpInstruction>()

        val absolutePath = File("").absolutePath
        val file  = File(absolutePath.plus(relativePath))
        try {
            BufferedReader(FileReader(file)).use {
                var readline = it.readLine()

                while (readline != null){
                    val jumpInstruction = JumpInstruction(readline.toInt())
                    listOfJumpInstructions.add(jumpInstruction)
                    readline = it.readLine()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listOfJumpInstructions
    }
}
