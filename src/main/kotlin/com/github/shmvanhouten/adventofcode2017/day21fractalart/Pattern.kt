package com.github.shmvanhouten.adventofcode2017.day21fractalart

data class Pattern(val gridLayout: List<List<Char>>) {

    val size = gridLayout.size

    fun flipped(): Pattern {
        return Pattern(gridLayout.map { it.reversed() })
    }

    fun rotated(): Pattern {
        val newRows = MutableList(gridLayout.size, {_ ->
            mutableListOf<Char>()
        })

        gridLayout.reversed().forEach { oldRow ->
            oldRow.forEachIndexed { index, char ->
                newRows[index].add(char)
            }
        }

        return Pattern(newRows)

    }
}