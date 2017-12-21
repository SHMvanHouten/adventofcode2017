package com.github.shmvanhouten.adventofcode2017.day21fractalart

interface Pattern {
    val gridLayout: List<List<Char>>
}

data class SquarePattern(override val gridLayout: List<List<Char>>): Pattern

data class ThreeByThreePattern(override val gridLayout: List<List<Char>>): Pattern

data class FourByFourPattern(override val gridLayout: List<List<Char>>): Pattern