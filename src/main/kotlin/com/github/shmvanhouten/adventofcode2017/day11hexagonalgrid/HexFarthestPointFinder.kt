package com.github.shmvanhouten.adventofcode2017.day11hexagonalgrid

class HexFarthestPointFinder(private val pathMapper: HexPathMapper = HexPathMapper(),
                             private val pathFinder: HexGridPathFinder = HexGridPathFinder()) {

    fun findFarthestPointInRoute(rawSteps: String): Int? {
        val path = pathMapper.drawPath(rawSteps)

        return path.map { pathFinder.findShortestPath(it) }.max()
    }
}