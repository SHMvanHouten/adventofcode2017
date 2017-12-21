package com.github.shmvanhouten.adventofcode2017.day21fractalart

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ArtGeneratorTest {

    @Test
    fun `it should draw a new grid from the old one using the test ruleBook`() {
        val ruleBookBuilder = RuleBookBuilder()
        val ruleBook = ruleBookBuilder.buildExtendedRuleBook("/day21/test1.txt")

        val generator = ArtGenerator(ruleBook)
        assertThat(generator.redrawGridAndCountPixels(1), equalTo(4))
    }

    @Test
    fun `it should draw two iterations of new grids from the old one using the test ruleBook`() {
        val ruleBookBuilder = RuleBookBuilder()
        val ruleBook = ruleBookBuilder.buildExtendedRuleBook("/day21/test1.txt")

        val generator = ArtGenerator(ruleBook)
        assertThat(generator.redrawGridAndCountPixels(2), equalTo(12))
    }

    @Test
    fun `it should solve the challenge input`() {
        val ruleBookBuilder = RuleBookBuilder()
        val ruleBook = ruleBookBuilder.buildExtendedRuleBook("/day21/day21.txt")

        val generator = ArtGenerator(ruleBook)
        assertThat(generator.redrawGridAndCountPixels(5), equalTo(184))
    }

    @Test
    fun `it should solve the challenge input pt 2`() {
        val ruleBookBuilder = RuleBookBuilder()
        val ruleBook = ruleBookBuilder.buildExtendedRuleBook("/day21/day21.txt")

        val generator = ArtGenerator(ruleBook)
        assertThat(generator.redrawGridAndCountPixels(18), equalTo(2810258))
    }



}