package com.github.shmvanhouten.adventofcode2017.day21fractalart

class ArtGenerator(private val ruleBook: Map<Pattern, Pattern>) {

    private val STANDARD_STARTING_GRID = listOf(
            listOf('.', '#', '.'),
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

        return patternGrid.flatMap { transformPatternRowByRules(it) }
    }

    private fun transformPatternRowByRules(patternRow: List<Pattern>): List<List<Char>> {
        val listOfPatternGrids = patternRow.map { getOutputPatternFromRuleBook(it) }
        val gridRow = MutableList(listOfPatternGrids[0].size, { _ -> mutableListOf<Char>() })

        listOfPatternGrids.forEach { outputGrid ->
            outputGrid.forEachIndexed { index, list ->
                gridRow[index].addAll(list)
            }
        }
        return gridRow
    }

    private fun getOutputPatternFromRuleBook(pattern: Pattern): List<List<Char>> {
        return ruleBook.getValue(pattern).gridLayout
    }

    private fun breakGridUpIntoPatterns(artGrid: List<List<Char>>): List<List<Pattern>> {
        val artGridSize = artGrid.size
        val patternSize = getPatternSize(artGridSize)

        val patternGrid = mutableListOf<MutableList<Pattern>>()
        0.until(artGridSize).step(patternSize).forEach { y ->
            val patternRow = buildPatternRow(patternSize, artGrid, y)
            patternGrid.add(patternRow)
        }

        return patternGrid.map { it.toList() }
    }

    private fun buildPatternRow(patternSize: Int, artGrid: List<List<Char>>, rowIndex: Int): MutableList<Pattern> {
        val artGridSize = artGrid.size
        val patternRow = mutableListOf<Pattern>()
        0.until(artGridSize).step(patternSize).forEach { startIndex ->
            val mutablePattern = buildPattern(patternSize, artGrid, rowIndex, startIndex)
            patternRow.add(mutablePattern.toPattern())
        }
        return patternRow
    }

    private fun buildPattern(patternSize: Int, artGrid: List<List<Char>>, rowIndex: Int, startIndex: Int): MutablePattern {
        val mutablePattern = MutablePattern()
        0.until(patternSize).forEach { patternRowIndex ->
            val row = artGrid[rowIndex + patternRowIndex].subList(startIndex, startIndex + patternSize)
            mutablePattern.addRow(row)
        }
        return mutablePattern
    }

    private fun getPatternSize(gridSize: Int): Int {
        return if (gridSize % 2 == 0) {
            2
        } else {
            3
        }
    }
}