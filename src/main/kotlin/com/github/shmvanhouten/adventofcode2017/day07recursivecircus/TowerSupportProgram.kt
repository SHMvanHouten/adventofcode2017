package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

import com.github.shmvanhouten.adventofcode2017.rawinstructionconverter.Instruction

data class TowerSupportProgram (val name: String, val weight: Int, val namesOfTowersItSupports: List<String>?): Instruction