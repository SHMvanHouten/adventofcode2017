package com.github.shmvanhouten.adventofcode2017.day07recursivecircus

data class TowerSupportProgram(val name: String,
                               val weight: Int,
                               val namesOfTowersItSupports: List<String>?,
                               private val towersItSupportsGetter: (List<String>) -> List<TowerSupportProgram>) {

    val towersItSupports: List<TowerSupportProgram> by lazy {
        if (namesOfTowersItSupports != null) {
            towersItSupportsGetter(namesOfTowersItSupports)
        } else {
            emptyList()
        }
    }

    val totalWeight: Int by lazy {
            towersItSupports.sumBy { it.totalWeight } + weight
    }


}