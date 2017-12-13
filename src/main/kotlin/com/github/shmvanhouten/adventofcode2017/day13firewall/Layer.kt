package com.github.shmvanhouten.adventofcode2017.day13firewall

import com.github.shmvanhouten.adventofcode2017.day13firewall.Direction.*

data class Layer(val depth: Int,
                 val range: Int,
                 var scannerPosition: Int = 0,
                 private var direction: Direction = DOWN) {


    fun moveScanner() {
        when (direction) {
            UP -> if (scannerPosition == 0) {
                scannerPosition++
                direction = direction.switch()
            } else {
                scannerPosition--
            }
            DOWN -> {
                if (scannerPosition + 1 == range) {
                    scannerPosition--
                    direction = direction.switch()
                } else {
                    scannerPosition++
                }
            }
        }

    }

    fun resetScanner() {
        scannerPosition = 0
        direction = DOWN
    }
}
