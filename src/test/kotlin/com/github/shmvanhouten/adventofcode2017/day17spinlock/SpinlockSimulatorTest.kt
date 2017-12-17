package com.github.shmvanhouten.adventofcode2017.day17spinlock

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class SpinlockSimulatorTest {

    @Test
    fun `it should insert a 1 after stepping three times`() {
        val simulator = SpinlockSimulator()
        assertThat(simulator.getValueAfterLastInserted(1, 3), equalTo(0))
    }

    @Test
    fun `it should insert a 1, 2 and 3 stepping three times before every insert and the next value after 3 should be 1`() {
        val simulator = SpinlockSimulator()
        assertThat(simulator.getValueAfterLastInserted(3, 3), equalTo(1))
    }

    @Test
    fun `it should insert a 1 through 9 stepping three times before every insert and the next value after 9 should be 5`() {
        val simulator = SpinlockSimulator()
        assertThat(simulator.getValueAfterLastInserted(9, 3), equalTo(5))
    }

    @Test
    fun `it should insert a 1 through 2017 stepping three times before every insert and the next value after 2017 should be 638`() {
        val simulator = SpinlockSimulator()
        assertThat(simulator.getValueAfterLastInserted(2017, 3), equalTo(638))
    }

    @Test
    fun `it should insert a 1 through 2017 stepping 376 (challenge input) times before every insert and the next value after 2017 should be 5`() {
        val simulator = SpinlockSimulator()
        assertThat(simulator.getValueAfterLastInserted(2017, 376), equalTo(777))
    }

}