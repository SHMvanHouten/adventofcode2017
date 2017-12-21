package com.github.shmvanhouten.adventofcode2017.day21fractalart

class ArtGenerator(private val ruleBook: Map<Pattern, Pattern>) {
    val STANDARD_STARTING_GRID = listOf(listOf('.', '#', '.'),
            listOf('.', '.', '#'),
            listOf('#', '#', '#'))

    fun redrawGridAndCountPixels(amountOfIterations: Int): Int {
        var grid = STANDARD_STARTING_GRID

        0.until(amountOfIterations).forEach {
            grid = redrawGrid(grid)
        }
        grid.forEach {
            println(it.joinToString(""))
        }

        return grid.flatMap { it }.count { it == '#' }
    }

    private fun redrawGrid(grid: List<List<Char>>): List<List<Char>> {
        val patternGrid = breakGridUpIntoPatterns(grid)

        return patternGrid.flatMap { turnRowOfPatternsIntoRowsOfPixels(it) }
    }

    private fun turnRowOfPatternsIntoRowsOfPixels(patternRow: List<Pattern>): List<List<Char>> {
        val listOfPatternGrids = patternRow.map { turnInputPatternIntoOutputPattern(it) }
        val gridRow = MutableList(listOfPatternGrids[0].size, { _ -> mutableListOf<Char>() })
        listOfPatternGrids.forEach { outputGrid ->
            outputGrid.forEachIndexed { index, list ->
                gridRow[index].addAll(list)
            }
        }
        return gridRow
    }

    private fun turnInputPatternIntoOutputPattern(pattern: Pattern): List<List<Char>> {
        return ruleBook.getValue(pattern).gridLayout
    }

    private fun breakGridUpIntoPatterns(artGrid: List<List<Char>>): List<List<Pattern>> {
        val artGridSize = artGrid.size
        val patternSize = getPatternSize(artGridSize)

        val patternGrid = mutableListOf<MutableList<Pattern>>()
        0.until(artGridSize).step(patternSize).forEach { y ->
            val patternRow = mutableListOf<Pattern>()
            0.until(artGridSize).step(patternSize).forEach { x ->
                val pattern = MutablePattern()
                0.until(patternSize).forEach { patternRow ->
                    pattern.addRow(artGrid[y + patternRow].subList(x, x + patternSize))
                }
                patternRow.add(pattern.toPattern())
            }
            patternGrid.add(patternRow)
        }

        return patternGrid.map { it.toList() }
    }

    private fun getPatternSize(gridSize: Int): Int {
        return if (gridSize % 2 == 0) {
            2
        } else {
            3
        }
    }
}