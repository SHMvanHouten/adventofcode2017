package com.github.shmvanhouten.adventofcode2017.day08registers


data class ConditionalRegisterModificationInstruction(val modificationInstruction: ModificationInstruction,
                                                      val conditionalInstruction: ConditionalInstruction)

data class ModificationInstruction(val registerToModify: String,
                                   val modification: Modification,
                                   val amountToModify: Int)

data class ConditionalInstruction(val registerToCheck: String,
                                  val comparisonOperator: ComparisonOperator,
                                  val numberToCheckRegisterAgainst: Int)

enum class Modification {
    INCREASE,
    DECREASE
}

enum class ComparisonOperator {
    EQUALS,
    DOES_NOT_EQUAL,
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_OR_EQUAL_TO,
    LESS_THAN_OR_EQUAL_TO
}