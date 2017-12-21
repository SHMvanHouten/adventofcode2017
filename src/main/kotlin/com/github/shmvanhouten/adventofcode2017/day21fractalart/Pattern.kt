package com.github.shmvanhouten.adventofcode2017.day21fractalart

data class Pattern(val gridLayout: List<List<Char>>) {
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
data class MutablePattern(var gridLayout: MutableList<List<Char>> = mutableListOf()) {

    fun addRow(chars: List<Char>){
        gridLayout.add(chars)
    }

    fun toPattern(): Pattern {
        return Pattern(gridLayout)
    }
}