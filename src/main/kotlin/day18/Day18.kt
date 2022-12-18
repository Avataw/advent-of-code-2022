package day18

fun solveA(input: List<String>): Int {
    val cubes = input.map(String::toCube).toSet()

    return cubes.size * 6 - cubes.sumOf { it.touches(cubes) }
}

fun solveB(input: List<String>): Int {
    val cubes = input.map(String::toCube).toSet()

    val min = minOf(cubes.minOf { it.x }, cubes.minOf { it.y }, cubes.minOf { it.z }) - 1
    val max = maxOf(cubes.maxOf { it.x }, cubes.maxOf { it.y }, cubes.maxOf { it.z }) + 1

    val fillers = mutableListOf(Cube(min, min, min))

    fillers.first().fill(cubes, fillers, min..max)

    return fillers.sumOf { it.touches(cubes) }
}

fun String.toCube(): Cube {
    val (x, y, z) = this.split(",").map(String::toInt)
    return Cube(x, y, z)
}

data class Cube(val x: Int, val y: Int, val z: Int) {

    fun touches(cubes: Set<Cube>) = listOfNotNull(
        cubes.find { it.x == x - 1 && it.y == y && it.z == z },
        cubes.find { it.x == x + 1 && it.y == y && it.z == z },
        cubes.find { it.y == y - 1 && it.x == x && it.z == z },
        cubes.find { it.y == y + 1 && it.x == x && it.z == z },
        cubes.find { it.z == z - 1 && it.y == y && it.x == x },
        cubes.find { it.z == z + 1 && it.y == y && it.x == x }
    ).count()

    fun fill(cubes: Set<Cube>, fillers: MutableList<Cube>, bounds: IntRange) {

        val adjacentCubes = this.calcAdjacent().filter {
            !fillers.contains(it)
                    && cubes.none { cube -> it.x == cube.x && it.y == cube.y && it.z == cube.z }
                    && it.x in bounds && it.y in bounds && it.z in bounds
        }

        fillers.addAll(adjacentCubes)

        adjacentCubes.forEach { it.fill(cubes, fillers, bounds) }
    }

    private fun calcAdjacent(): List<Cube> = listOf(
        Cube(x + 1, y, z),
        Cube(x - 1, y, z),
        Cube(x, y + 1, z),
        Cube(x, y - 1, z),
        Cube(x, y, z + 1),
        Cube(x, y, z - 1),
    )
}
