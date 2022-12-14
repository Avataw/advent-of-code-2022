package day14

import kotlin.math.max
import kotlin.math.min

fun solveA(input: List<String>): Int {

    val occupiedPositions = input.parseToCave().toMutableSet()

    val lowestRockHeight = occupiedPositions.maxBy { it.y }.y

    return occupiedPositions.pourSandWithoutFloor(lowestRockHeight)
}

fun solveB(input: List<String>): Int {

    val occupiedPositions = input.parseToCave().toMutableSet()

    val floor = occupiedPositions.maxBy { it.y }.y

    (-1000..1000).map { Position(it, floor + 2) }.also { occupiedPositions.addAll(it) }

    return occupiedPositions.pourSandWithFloor(floor + 2)
}

fun MutableSet<Position>.pourSandWithoutFloor(lowestRock: Int): Int {
    var restingSand = 0

    while (true) {
        val sandUnit = Sand(this, lowestRock)
        val cameToRest = sandUnit.move()

        if (!cameToRest) return restingSand

        this.add(sandUnit.position).also { restingSand++ }
    }
}

fun MutableSet<Position>.pourSandWithFloor(floor: Int): Int {
    var restingSand = 0

    while (true) {
        val sandUnit = Sand(this, floor + 2)

        sandUnit.move()

        this.add(sandUnit.position).also { restingSand++ }

        if (sandUnit.position == sandUnit.startingPosition) return restingSand
    }
}

fun List<String>.parseToCave(): Set<Position> = this.flatMap { it.scan().combinePaths() }.toSet()

fun String.scan() = this.split(" -> ").map(String::toPosition)

fun String.toPosition() = this.split(",").let { Position(it.first().toInt(), it.last().toInt()) }

fun List<Position>.combinePaths() = this.windowed(2).flatMap { it.first().to(it.last()) }

data class Sand(val occupiedPositions: Set<Position>, val floor: Int = 0) {
    val startingPosition = Position(500, 0)
    var position: Position = startingPosition

    fun move(): Boolean {
        while (position.y <= floor) {
            position = if (!occupiedPositions.contains(position.down())) position.down()
            else if (!occupiedPositions.contains(position.downLeft())) position.downLeft()
            else if (!occupiedPositions.contains(position.downRight())) position.downRight()
            else return true
        }
        return false
    }
}

data class Position(val x: Int, val y: Int) {
    fun down() = Position(x, y + 1)
    fun downLeft() = Position(x - 1, y + 1)
    fun downRight() = Position(x + 1, y + 1)

    private fun toHorizontally(other: Position) = (min(x, other.x)..max(x, other.x)).map { Position(it, y) }
    private fun toVertically(other: Position) = (min(y, other.y)..max(y, other.y)).map { Position(x, it) }
    fun to(other: Position): Set<Position> = (toHorizontally(other) + toVertically(other)).toSet()
}
