package com.github.shmvanhouten.adventofcode2017.day16dancingprograms

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.FileReader.readFile
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DanceSimulatorTest {


    @Test
    fun `it should perform a spin move on abcde and become eabcd`() {
        val danceSimulator = DanceSimulator()
        assertThat(danceSimulator.getOrderOfProgramsAfterDance("s1", 5), equalTo("eabcd"))
    }

    @Test
    fun `it should perform an exchange move on eabcd and become eabdc`() {
        val danceSimulator = DanceSimulator()
        assertThat(danceSimulator.getOrderOfProgramsAfterDance("s1,x3/4", 5), equalTo("eabdc"))
    }

    @Test
    fun `it should perform a partner move on eabdc and become baedc`() {
        val danceSimulator = DanceSimulator()
        assertThat(danceSimulator.getOrderOfProgramsAfterDance("s1,x3/4,pe/b", 5), equalTo("baedc"))
    }

    @Test
    fun `it should solve the challenge input`() {
        val danceSimulator = DanceSimulator()
        assertThat(danceSimulator.getOrderOfProgramsAfterDance(readFile("/day16/day16.txt"), 16), equalTo("fnloekigdmpajchb"))
    }


    @Test
    fun `it should perform these moves two times on abcde and become ceadb`() {
        val danceSimulator = DanceSimulator()
        assertThat(danceSimulator.getOrderOfProgramsAfterXDances("s1,x3/4,pe/b", 5, 2), equalTo("ceadb"))
    }


    @Test
    fun `it should run the challenge input 1 time`() {
        val danceSimulator = DanceSimulator()
        assertThat(danceSimulator.getOrderOfProgramsAfterXDances(readFile("/day16/day16.txt"), 16, 1), equalTo("fnloekigdmpajchb"))
    }

    @Test
    fun `it should find the result for running the challenge input 1_000 times`() {
        val danceSimulator = DanceSimulator()
        val danceGroupAfter1000Dances = danceSimulator
                .getOrderOfProgramsAfterXDances(readFile("/day16/day16.txt"), 16, 1_000)
        assertThat(danceGroupAfter1000Dances, equalTo("amkjepdhifolgncb"))
    }

    @Test
    fun `it should find the result for running the challenge input 1_000_000_000 times`() {
        val danceSimulator = DanceSimulator()
        val danceGroupAfter1000Dances = danceSimulator
                .getOrderOfProgramsAfterXDances(readFile("/day16/day16.txt"), 16, 1_000_000_000)
        assertThat(danceGroupAfter1000Dances, equalTo("amkjepdhifolgncb"))
    }



//    @Test
//    fun `it should perform the moves 4 times until the result is the same as the input`() {
//        val danceSimulator = DanceSimulator()
//
//        assertThat(danceSimulator.findAmountOfDancesItTakesToGetBackToOriginalState("s1,x3/4,pe/b", 5), equalTo(4))
//    }
//
//    @Test
//    fun `it should perform the moves x times until the result is the same as the challengeInput`() {
//        val danceSimulator = DanceSimulator()
//
//        assertThat(danceSimulator.findAmountOfDancesItTakesToGetBackToOriginalState(readFile("/day16/day16.txt"), 16), equalTo(60))
//    }

}

