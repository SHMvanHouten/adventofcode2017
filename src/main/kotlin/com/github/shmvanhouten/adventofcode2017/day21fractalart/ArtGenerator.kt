package com.github.shmvanhouten.adventofcode2017.day21fractalart

class ArtGenerator(private val ruleBook: RuleBook) {

    private val STANDARD_STARTING_GRID = listOf(
            listOf('.', '#', '.'),
            listOf('.', '.', '#'),
            listOf('#', '#', '#'))


    fun redrawGridAndCountPixels(amountOfIterations: Int): Int {
        var grid = STANDARD_STARTING_GRID

        0.until(amountOfIterations).forEach {
            grid = redrawGrid(grid)
            val count = grid.flatMap { it }.count { it == '#' }
            println("after ${it + 1} iterations, there are $count pixels lit")
        }
//        grid.forEach {
//            println(it.joinToString(""))
//        }

        return grid.flatMap { it }.count { it == '#' }
    }

    private fun redrawGrid(grid: List<List<Char>>): List<List<Char>> {
        val patternGrid = breakGridUpIntoPatterns(grid)

        return patternGrid.flatMap { transformRowToNewGridLayouts(it) }
    }

    private fun transformRowToNewGridLayouts(patternRow: List<Pattern>): List<List<Char>> {
        val listOfPatternGrids = patternRow.map { convertPatternByRuleBook(it) }
        val gridRow = MutableList(listOfPatternGrids[0].size, { _ -> mutableListOf<Char>() })

        listOfPatternGrids.forEach { outputGrid ->
            outputGrid.forEachIndexed { index, characters ->
                gridRow[index].addAll(characters)
            }
        }
        return gridRow
    }

    private fun convertPatternByRuleBook(pattern: Pattern): List<List<Char>> {
        return ruleBook.getValue(pattern).gridLayout
    }

    private fun breakGridUpIntoPatterns(artGrid: List<List<Char>>): List<List<Pattern>> {
        val artGridSize = artGrid.size
        val patternSize = getPatternSize(artGridSize)

        return 0.until(artGridSize).step(patternSize).map { y ->
            turnRowsIntoPatternRow(patternSize, artGrid, y)
        }

    }

    private fun turnRowsIntoPatternRow(patternSize: Int, artGrid: List<List<Char>>, y: Int): List<Pattern> {
        return 0.until(artGrid.size).step(patternSize).map { x ->
            buildPatternFromPairOrTrioOfRows(patternSize, artGrid, y, x)
        }
    }

    private fun buildPatternFromPairOrTrioOfRows(patternSize: Int, artGrid: List<List<Char>>, y: Int, x: Int): Pattern {
        val patternGridLayout = 0.until(patternSize).map { patternRowIndex ->
            artGrid[y + patternRowIndex].subList(x, x + patternSize)
        }
        return Pattern(patternGridLayout)
    }

    private fun getPatternSize(gridSize: Int): Int {
        return if (gridSize % 2 == 0) {
            2
        } else {
            3
        }
    }
}