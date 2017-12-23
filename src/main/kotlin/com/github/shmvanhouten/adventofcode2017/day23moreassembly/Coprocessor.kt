package com.github.shmvanhouten.adventofcode2017.day23moreassembly

import com.github.shmvanhouten.adventofcode2017.day18duet.AssemblyCodeRunner
import com.github.shmvanhouten.adventofcode2017.day18duet.AssemblyInstruction
import com.github.shmvanhouten.adventofcode2017.day18duet.AssemblyInstructionConverter
import com.github.shmvanhouten.adventofcode2017.day18duet.InstructionType

class Coprocessor(assemblyCode: List<AssemblyInstruction>) : AssemblyCodeRunner(assemblyCode) {

    fun runAssemblyProgram(): Long {

        val registers = mutableMapOf<String, Long>("a" to 1L)

        var index = 0

        var amountOfMultiplyInvoked = 0

        while (index < assemblyCode.size) {

//            if(index > 23) println("g: ${registers.get("g")}, h: ${registers.get("h")}, b: ${registers.get("b")}, c: ${registers.get("c")}, f: ${registers.get("f")}")
//            if (index == 23) println("c: ${registers.get("c")},g: ${registers.get("g")}, d: ${registers.get("d")}, b: ${registers.get("b")}, e: ${registers.get("e")}, f: ${registers.get("f")}, h: ${registers.get("h")}")
            val instruction = assemblyCode[index]

            when (instruction.instructionType) {
                InstructionType.SET -> performSetInstruction(registers, instruction)
                InstructionType.SUBTRACT -> performSubtractInstruction(registers, instruction)
                InstructionType.MULTIPLY -> {
                    performMultiplyInstruction(registers, instruction)
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
    /* considering the input in resources/day23/day23.txt

    lines 1..8:
    b =
    set b 99
    mul b 100
    sub b -100000 (b = 109900)
    c =
    set c b
    sub c -17000 (c = 126900)

    after being set these lines will not be visited again

    the process will run until b == c, because:
    line 27..30:
    set g b
    sub g c
    jnz g 2
    jnz 1 3 -> will jump out of the program if b-c = 0

    which means b will be the values 109900..126900 in steps of 17 for the main loop from line 9 (set f 1) to line 32 (jnz 1 -23)
    b steps in 17 because line 31: sub b -17


    inside the main loop (line 9..32)now: loop23 there is a loop (line 11..24)now: loop13 with a smaller loop inside that(line 12..20)now: loop8

    the condition for a loop23 to add 1 to h (sub h -1 ,line 26) is:
    jnz f 2
    sub h -1
    so if f is 0, h will be added 1

    every loop23 will set f to 1, the only way f can be set to zero is if:
    set g d
    mul g e
    sub g b
    jnz g 2
    set f 0
    so if d*e == b, f will be set to 0

    loop 13 iterates index d from 2..index b (because line 21: (sub d -1))
    loop 8 iterates index e from 2..index b (because line 17: (sub e -1))

    which means for any value of b, if it is not prime, it will add 1 to h

    resulting in the algorithm: (109900..126900 step 17).count { !isPrime(it) }
 */
    println((109900..126900 step 17).count { !isPrime(it) })

//    val converter = AssemblyInstructionConverter()
//    val assemblyCode = converter.parseAssemblyCodeFromString("/day23/day23.txt")
//
//    val coprocessor = Coprocessor(assemblyCode)
//    println(coprocessor.runAssemblyProgram())
}

fun isPrime(num: Int): Boolean {
    return (2..num / 2).none {
        num % it == 0
    }
}