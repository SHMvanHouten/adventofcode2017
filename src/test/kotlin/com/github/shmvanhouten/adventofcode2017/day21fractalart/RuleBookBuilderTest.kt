package com.github.shmvanhouten.adventofcode2017.day21fractalart

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class RuleBookBuilderTest {

    @Test
    fun `it should build a set of all possible combinations an the rule they point to`() {
        val ruleBuilder = RuleBookBuilder()
        val allInputsToRule = ruleBuilder.buildExtendedRuleBook("/day21/squareInputs.txt")
        allInputsToRule.keys.forEach {
                printPattern(it)
                println(" ")
            }
        assertThat(allInputsToRule.size, equalTo(16))
    }

    @Test
    fun `it should build a set of all possible combinations an the rule they point to for the challenge input`() {
        val ruleBuilder = RuleBookBuilder()
        val allInputsToRule = ruleBuilder.buildExtendedRuleBook("/day21/day21.txt")
        allInputsToRule.keys.forEach {
            printPattern(it)
            println(" ")
        }
        assertThat(allInputsToRule.size, equalTo(528))
    }

}

