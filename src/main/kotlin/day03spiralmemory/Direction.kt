package day03spiralmemory

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;


    fun turnLeft(): Direction {
        return if (this == NORTH) {
            WEST
        } else {
            Direction.values()[this.ordinal - 1]
        }
    }
}
