package aoc

import aoc.Direction.*

fun solve24A(input: List<String>): Int {

    val village = input.toVillage()

    val start = village.start

    return village.findShortestPathToEnd(Mover(start, 0)).moves
}

fun solve24B(input: List<String>): Int {

    val village = input.toVillage()

    val start = village.start

    val shortestFirstTrip = village.findShortestPathToEnd(Mover(start, 0))
    val shortestSecondTrip = village.findShortestPathToStart(shortestFirstTrip)
    return village.findShortestPathToEnd(shortestSecondTrip).moves
}

data class Village(val spots: Set<Spot>) {
    val start = spots.minBy { it.pos.y }.pos
    private val end = spots.maxBy { it.pos.y }.pos
    private val top = start.y + 1
    private val bot = end.y - 1
    private val left = spots.minOf { it.pos.x }
    private val right = spots.maxOf { it.pos.x }

    fun findShortestPathToEnd(mover: Mover) = findShortestPath(mover, end)
    fun findShortestPathToStart(mover: Mover) = findShortestPath(mover, start)

    private fun findShortestPath(mover: Mover, target: Pos): Mover {
        var rounds = 0
        var movers = mutableListOf(mover)
        while (true) {
            movers = movers.toSet().toMutableList()

            if (movers.any { it.pos == target }) return movers.first { it.pos == target }

            spots.forEach { spot -> spot.move(spots, top, bot, left, right) }
            movers.removeAll { !it.active }
            val newMovers = movers.flatMap { it.move(spots) }
            movers.addAll(newMovers)
            spots.forEach { spot -> spot.setBlizzards() }
            rounds++
        }
    }
}

data class Mover(var pos: Pos, var moves: Int, var active: Boolean = true) {

    private fun getAvailableMoves() = listOf(pos.up(), pos.right(), pos.down(), pos.left())

    fun move(spots: Set<Spot>): List<Mover> {

        val availableMoves = getAvailableMoves()
        val adjacentSpots = spots.filter { availableMoves.contains(it.pos) }.filter { it.nextBlizzards.isEmpty() }

        val newMovers = adjacentSpots.map { Mover(it.pos, moves + 1) }

        val currentSpot = spots.find { it.pos == pos && it.nextBlizzards.isNotEmpty() }
        if (currentSpot != null) active = false
        else moves++
        return newMovers
    }
}

data class Spot(val pos: Pos, var blizzards: MutableList<Blizzard>, var nextBlizzards: MutableList<Blizzard>) {

    fun move(spots: Set<Spot>, top: Int, bot: Int, left: Int, right: Int) {

        val toRemove = mutableListOf<Blizzard>()

        blizzards.forEach {
            val nextPosition = when (it.dir) {
                RIGHT -> pos.right()
                DOWN -> pos.down()
                LEFT -> pos.left()
                UP -> pos.up()
            }

            val nextSpot = spots.find { spot -> spot.pos == nextPosition }

            if (nextSpot != null) nextSpot.nextBlizzards.add(it)
            else {
                val wrappedPosition = when (it.dir) {
                    RIGHT -> pos.copy(x = left)
                    DOWN -> pos.copy(y = top)
                    LEFT -> pos.copy(x = right)
                    UP -> pos.copy(y = bot)
                }
                val wrappedSpot = spots
                    .find { spot -> spot.pos == wrappedPosition } ?: throw Exception("Spot not found")
                wrappedSpot.nextBlizzards.add(it)
            }
            toRemove.add(it)
        }
        toRemove.forEach { blizzards.remove(it) }
    }

    fun setBlizzards() {
        blizzards.addAll(nextBlizzards)
        nextBlizzards.clear()
    }
}

fun List<String>.toVillage(): Village {

    val spots = mutableSetOf<Spot>()

    this.forEachIndexed { y, s ->
        s.forEachIndexed { x, c ->
            val pos = Pos(x, y)
            when (c) {
                '.' -> spots.add(Spot(pos, mutableListOf(), mutableListOf()))
                '>' -> spots.add(Spot(pos, mutableListOf(Blizzard(RIGHT)), mutableListOf()))
                '<' -> spots.add(Spot(pos, mutableListOf(Blizzard(LEFT)), mutableListOf()))
                'v' -> spots.add(Spot(pos, mutableListOf(Blizzard(DOWN)), mutableListOf()))
                '^' -> spots.add(Spot(pos, mutableListOf(Blizzard(UP)), mutableListOf()))
            }
        }
    }
    return Village(spots)
}

data class Blizzard(val dir: Direction)
