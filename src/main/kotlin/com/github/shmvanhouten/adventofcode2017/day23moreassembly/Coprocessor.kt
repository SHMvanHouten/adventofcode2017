package com.github.shmvanhouten.adventofcode2017.day23moreassembly

import com.github.shmvanhouten.adventofcode2017.day18duet.AssemblyCodeRunner
import com.github.shmvanhouten.adventofcode2017.day18duet.AssemblyInstruction
import com.github.shmvanhouten.adventofcode2017.day18duet.AssemblyInstructionConverter
import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType

class Coprocessor(assemblyCode: List<AssemblyInstruction>): AssemblyCodeRunner(assemblyCode) {

    fun runAssemblyProgram(): Long {

        val registers = mutableMapOf<String, Long>("a" to 1L)

        var index = 0

        var amountOfMultiplyInvoked = 0

        while (index < assemblyCode.size) {

//            if(index > 23) println("g: ${registers.get("g")}, h: ${registers.get("h")}, b: ${registers.get("b")}, c: ${registers.get("c")}, f: ${registers.get("f")}")
            if(index == 23) println("c: ${registers.get("c")},g: ${registers.get("g")}, d: ${registers.get("d")}, b: ${registers.get("b")}, e: ${registers.get("e")}, f: ${registers.get("f")}, h: ${registers.get("h")}")
            val instruction = assemblyCode[index]

            when (instruction.instructionType) {
                InstructionType.SET -> performSetInstruction(registers, instruction)
                InstructionType.SUBTRACT -> performSubtractInstruction(registers, instruction)
                InstructionType.MULTIPLY -> {performMultiplyInstruction(registers, instruction)
                    amountOfMultiplyInvoked++
                }
                InstructionType.JUMP_NOT_ZERO -> index = performJumpInstruction(registers, instruction, index)
            }
            index++
        }

        return registers.getValue("h")
    }

    private fun performSubtractInstruction(registers: MutableMap<String, Long>, instruction: AssemblyInstruction) {
        performOperation(instruction, registers, { first, second -> first - second })
    }

    override fun doesValuePassCondition(checkLong: Long) = checkLong != 0L
}

fun main(args: Array<String>) {
    println((109900..126900 step 17).count { isPrime(it) })

    val converter = AssemblyInstructionConverter()
    val assemblyCode = converter.parseAssemblyCodeFromString("/day23/day23.txt")

    val coprocessor = Coprocessor(assemblyCode)
    println(coprocessor.runAssemblyProgram())
}

fun isPrime(num: Int): Boolean {
    var flag = false
    for (i in 2..num / 2) {
        // condition for nonprime number
        if (num % i == 0) {
            flag = true
            break
        }
    }
    return flag
}