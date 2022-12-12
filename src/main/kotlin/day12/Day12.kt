package day12

import java.util.*

fun solveA(input: List<String>): Int {

    val heightMap = createHeightMap(input).also { it.initialize() }

    val start = heightMap.values.find { it.start } ?: throw Exception("No start exists!")
    val end = heightMap.values.find { it.end } ?: throw Exception("No end exists!")

    return calcShortestPath(start, end)
}

fun solveB(input: List<String>): Int {

    val heightMap = createHeightMap(input).also { it.initialize() }

    val end = heightMap.values.find { it.end } ?: throw Exception("No end exists!")

    return heightMap.values.filter { it.elevation == 1 }.minOf { start -> calcShortestPath(start, end) }
}


fun calcShortestPath(start: Square, end: Square): Int {
    start.shortestPath = 0

    val compare: Comparator<Square> = compareBy<Square> { it.letter }.thenBy { it.shortestPath }
    val queue = PriorityQueue(compare).also { it.add(start) }

    while (!queue.contains(end) && queue.isNotEmpty()) {
        val current = queue.poll()
        current.moves.forEach {
            if (it.shortestPath > current.shortestPath + 1) it.shortestPath = current.shortestPath + 1
        }
        queue.addAll(current.moves.filter { !queue.contains(it) && it.shortestPath >= current.shortestPath + 1 })
    }

    return end.shortestPath
}

data class Position(val x: Int, val y: Int) {
    fun surrounding() = listOf(
        Position(x, y + 1),
        Position(x + 1, y),
        Position(x, y - 1),
        Position(x - 1, y)
    )
}

data class Square(val letter: Char, val position: Position) {
    val elevation = when (letter) {
        'S' -> lowestElevation
        'E' -> highestElevation
        else -> letter.code - charCodeToElevationDifference
    }
    val start = letter == 'S'
    val end = letter == 'E'
    var shortestPath = Int.MAX_VALUE

    val moves = mutableListOf<Square>()

    fun calcPossibleMoves(squares: List<Square?>) =
        moves.addAll(squares.filterNotNull().filter { it.elevation <= elevation + 1 })

    companion object {
        const val lowestElevation = 1
        const val highestElevation = 26
        const val charCodeToElevationDifference = 96
    }

}

fun createHeightMap(input: List<String>) = input
    .flatMapIndexed { y, str ->
        str.mapIndexed { x, char -> Square(char, Position(x, y)) }
    }
    .associateBy { it.position }

fun Map<Position, Square>.initialize() = this.entries.forEach {
    it.value.calcPossibleMoves(it.key.surrounding().map { pos -> this[pos] })
}


//fun solveA(input: List<String>): Int {
//
//    val heightMap = mutableMapOf<Position, Square>()
//
//    input.forEachIndexed { y, s ->
//        s.forEachIndexed { x, c ->
//            val pos = Position(x, y)
//            val square = Square(c, pos)
//            heightMap[pos] = square
//        }
//    }
//
//    heightMap.entries.forEach {
//        it.value.calcPossibleMoves(it.key.surrounding().map { pos -> heightMap[pos] })
//    }
//
//    val start = heightMap.values.find { it.start } ?: throw Exception("No start exists!")
//    val end = heightMap.values.find { it.end } ?: throw Exception("No end exists!")
//    start.shortestPath = 0
//
//    val compare: Comparator<Square> = compareBy<Square> { it.letter }.thenBy { it.shortestPath }
//    val queue = PriorityQueue(compare)
//
//    queue.add(start)
//
//    while (!queue.contains(end)) {
//        val current = queue.poll()
//        current.moves.forEach {
//            if (it.shortestPath > current.shortestPath + 1) it.shortestPath = current.shortestPath + 1
//        }
//        queue.addAll(current.moves.filter { !queue.contains(it) && it.shortestPath >= current.shortestPath + 1 })
//    }
//
//    return end.shortestPath
//}
//
//data class Position(val x: Int, val y: Int) {
//    fun surrounding() = listOf(
//        Position(x, y + 1),
//        Position(x + 1, y),
//        Position(x, y - 1),
//        Position(x - 1, y)
//    )
//}
//
//data class Square(val letter: Char, val position: Position) {
//    val elevation = when (letter) {
//        'S' -> 1
//        'E' -> 26
//        else -> letter.code - 96
//    }
//    val start = letter == 'S'
//    val end = letter == 'E'
//    var shortestPath = Int.MAX_VALUE
//
//    val moves = mutableListOf<Square>()
//
//    fun calcPossibleMoves(squares: List<Square?>) =
//        moves.addAll(
//            squares.filterNotNull().filter { it.elevation <= elevation + 1 })
//
//    override fun toString(): String {
//        return "Square $letter: $position -> $shortestPath"
//    }
//}
//
//fun solveB(input: List<String>): Int {
//    val heightMap = mutableMapOf<Position, Square>()
//
//    input.forEachIndexed { y, s ->
//        s.forEachIndexed { x, c ->
//            val pos = Position(x, y)
//            val square = Square(c, pos)
//            heightMap[pos] = square
//        }
//    }
//
//    heightMap.entries.forEach {
//        it.value.calcPossibleMoves(it.key.surrounding().map { pos -> heightMap[pos] })
//    }
//
//    val end = heightMap.values.find { it.end } ?: throw Exception("No end exists!")
//
//    return heightMap.values.filter { it.elevation == 1 }.map {
//        val path = doStuff(it, end)
//
//        heightMap.values.forEach { it.shortestPath = Int.MAX_VALUE }
//        path
//    }.min()
//}
//
//fun doStuff(start: Square, end: Square): Int {
//    start.shortestPath = 0
//
//    val compare: Comparator<Square> = compareBy<Square> { it.letter }.thenBy { it.shortestPath }
//    val queue = PriorityQueue(compare)
//
//    queue.add(start)
//
//    while (!queue.contains(end) && queue.isNotEmpty()) {
//        val current = queue.poll()
//        current.moves.forEach {
//            if (it.shortestPath > current.shortestPath + 1) it.shortestPath = current.shortestPath + 1
//        }
//        queue.addAll(current.moves.filter { !queue.contains(it) && it.shortestPath >= current.shortestPath + 1 })
//    }
//
//    return end.shortestPath
//}
