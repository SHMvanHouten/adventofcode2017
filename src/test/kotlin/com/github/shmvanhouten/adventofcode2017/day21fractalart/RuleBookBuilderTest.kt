package com.github.shmvanhouten.adventofcode2017.day21fractalart

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class RuleBookBuilderTest {

    @Test
    fun `it should build a set of all possible combinations an the rule they point to`() {
        val ruleBuilder = RuleBookBuilder()
        val allInputsToRule = ruleBuilder.buildAllPossibleRulesFromRawInput("/day21/squareInputs.txt")
//        allInputsToRule.keys.forEach {
//            println(it)
//        }
        assertThat(allInputsToRule.size, equalTo(24))
    }
}