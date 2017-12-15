package com.github.shmvanhouten.adventofcode2017.day15duelinggenerators

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class PickyNumberGeneratorTest {

    @Test
    fun `it should calculate the next number 1352636452 for startingNumber 65 and factor 16807, with criteria 4`() {
        val generator = PickyNumberGenerator(65, 16807, 4)
        assertThat(generator.getNext(), equalTo(1352636452L))
    }

    @Test
    fun `it should calculate the fifth number 740335192 for startingNumber 65 and factor 16807, with criteria 4`() {
        val generator = PickyNumberGenerator(65, 16807, 4)
        println(generator.getNext())
        println(generator.getNext())
        println(generator.getNext())
        println(generator.getNext())
        assertThat(generator.getNext(), equalTo(740335192L))
    }

    @Test
    fun `it should calculate the fifth number 285222916 for startingNumber 8921 and factor 48271, with criteria 8`() {
        val generator = PickyNumberGenerator(8921, 48271, 8)
        println(generator.getNext())
        println(generator.getNext())
        println(generator.getNext())
        println(generator.getNext())
        assertThat(generator.getNext(), equalTo(412269392L))
    }
}