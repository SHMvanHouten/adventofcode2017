package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

class DanceMoveCondenser {

    fun splitAndCondenseDanceMoves(danceMoves: List<DanceMove>, basicDanceGroup: List<Char>): List<DanceMove> {
        val partnerAndOtherMoves = danceMoves.groupBy { it is PartnerMove }

        if (partnerAndOtherMoves.size == 2) {
            val condensedExchangeAndSpinMoves = condenseExchangeAndSpinMoves(partnerAndOtherMoves.getValue(false), basicDanceGroup)
            val condensedPartnerMoves = condensePartnerMoves(partnerAndOtherMoves.getValue(true), basicDanceGroup)

            return listOf(condensedExchangeAndSpinMoves, condensedPartnerMoves)
        }

        return if(partnerAndOtherMoves.containsKey(false)){
            val condensedExchangeAndSpinMoves = condenseExchangeAndSpinMoves(partnerAndOtherMoves.getValue(false), basicDanceGroup)
            listOf(condensedExchangeAndSpinMoves)
        } else {
            val condensedPartnerMoves = condensePartnerMoves(partnerAndOtherMoves.getValue(true), basicDanceGroup)
            listOf(condensedPartnerMoves)
        }


    }

    private fun condenseExchangeAndSpinMoves(exchangeAndSpinMoves: List<DanceMove>, basicDanceGroup: List<Char>): CondensedDanceMove {
        val danceGroupAfterMoves = exchangeAndSpinMoves
                .fold(basicDanceGroup, { dancingGroup, danceMove -> danceMove.getMove()(dancingGroup) })

        val newPositionsOfPrograms = basicDanceGroup
                .map { it.getPositionInAlphabet() to danceGroupAfterMoves.indexOf(it) }
                .sortedBy { (_, newIndex) -> newIndex }
                .map { it.first }

        return CondensedDanceMove(this::performCondensedDanceMove, newPositionsOfPrograms)
    }

    private fun condensePartnerMoves(partnerMoves: List<DanceMove>, groupBeforeMoves: List<Char>): CondensedPartnerMove {

        val danceGroupAfterMoves = partnerMoves
                .fold(groupBeforeMoves, { dancingGroup, danceMove -> danceMove.getMove()(dancingGroup) })

        val linkedPrograms = groupBeforeMoves
                // target danceProgram in groupBeforeMoves is linked to the program that is at the index target is at after moves
                .associateBy({ it }, { groupBeforeMoves[danceGroupAfterMoves.indexOf(it)] })

        return CondensedPartnerMove(this::performCondensedPartnerMove, linkedPrograms)
    }

    private fun performCondensedDanceMove(danceGroup: List<Char>, newPositionsOfPrograms: List<Int>): List<Char> {
        return newPositionsOfPrograms.map { danceGroup[it] }
    }

    private fun performCondensedPartnerMove(danceGroup: List<Char>, linkedPrograms: Map<Char, Char>): List<Char> {
        return danceGroup.map { it to danceGroup.indexOf(linkedPrograms.getValue(it)) }
                .sortedBy { (_ , newIndex ) -> newIndex }
                .map { it.first }
    }

}

private fun Char.getPositionInAlphabet(): Int {
    return this.toInt() - 97
}