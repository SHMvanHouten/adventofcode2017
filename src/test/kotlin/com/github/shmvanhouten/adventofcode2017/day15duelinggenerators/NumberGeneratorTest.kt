package com.github.shmvanhouten.adventofcode2017.day15duelinggenerators

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class NumberGeneratorTest {

    @Test
    fun `it should calculate the next number 1092455 for startingNumber 65 and factor 16807`() {
        val generator = NumberGenerator(65, 16807)
        assertThat(generator.getNext(), equalTo(1092455L))
    }

    @Test
    fun `it should calculate the fifth number 1352636452 for startingNumber 65 and factor 16807`() {
        val generator = NumberGenerator(65, 16807)
        println(generator.getNext())
        println(generator.getNext())
        println(generator.getNext())
        println(generator.getNext())
        assertThat(generator.getNext(), equalTo(1352636452L))
    }

    @Test
    fun `it should calculate the fifth number 285222916 for startingNumber 8921 and factor 48271`() {
        val generator = NumberGenerator(8921, 48271)
        println(generator.getNext())
        println(generator.getNext())
        println(generator.getNext())
        println(generator.getNext())
        assertThat(generator.getNext(), equalTo(285222916L))
    }



}