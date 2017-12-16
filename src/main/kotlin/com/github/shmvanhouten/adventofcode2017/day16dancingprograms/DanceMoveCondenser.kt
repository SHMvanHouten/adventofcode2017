package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

class DanceMoveCondenser {

    fun condenseDanceMoves(danceMoves: List<DanceMove>, basicDanceGroup: List<Char>): CondensedDanceMove {
        val danceGroupAfterMoves = danceMoves.fold(basicDanceGroup, { dancingGroup, danceMove -> danceMove.getMove()(dancingGroup) })
        val newPositionsOfChars = basicDanceGroup
                .map{ it.getPositionInAlphabet() to danceGroupAfterMoves.indexOf(it) }
                .sortedBy { (_, newIndex) -> newIndex}
                .map { it.first }
        return CondensedDanceMove(this::performCondensedDanceMove, newPositionsOfChars)
    }

    private fun performCondensedDanceMove(danceGroup: List<Char>, newPositionsOfPrograms: List<Int>): List<Char> {
        return newPositionsOfPrograms.map { danceGroup[it] }
    }

}

private fun Char.getPositionInAlphabet(): Int {
    return this.toInt() - 97
}
