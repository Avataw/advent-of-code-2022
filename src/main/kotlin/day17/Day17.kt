package day17

fun solveA(input: List<String>): Int {
    val jetStream = JetStream(input.first())
    val shaper = Shaper()

    val blocked = mutableSetOf<Position>()

    var highestRock = 0

    repeat(2022) {
        highestRock += blocked.nextStep(highestRock, shaper, jetStream)
    }

    return highestRock
}

fun MutableSet<Position>.nextStep(highestRock: Int, shaper: Shaper, jetStream: JetStream): Int {
    val start = Position(3, highestRock + 4)
    val shape = shaper.next(start)

    val newBlocked = shape.move(jetStream, this)
    this.addAll(newBlocked)

    return this.maxOf { it.y } - highestRock
}


fun solveB(input: List<String>): Long {
    val jetStream = JetStream(input.first())
    val shaper = Shaper()

    val blocked = mutableSetOf<Position>()

    var highestRock = 0

    val previousSteps = mutableSetOf<String>()

    var counting = 0
    var cycleStart = 0
    var cycleStartCode = ""

    var startHighestRock = 0
    var cycleHeight = 0

    for (times in 0..5000) {

        val heightDifference = blocked.nextStep(highestRock, shaper, jetStream)
        highestRock += heightDifference

        val code = "${shaper.current},${jetStream.current},$heightDifference"

        if (!previousSteps.contains(code)) {
            counting = 0
            previousSteps.add(code)
            continue
        }

        if (counting == 0) {
            startHighestRock = highestRock
            cycleStart = times
            cycleStartCode = code
        } else if (code == cycleStartCode) {
            cycleHeight = highestRock - startHighestRock
            break
        }

        counting++

    }

    val cycleTimes = (1000000000000 - 1 - cycleStart) / counting
    val remainderTimes = ((1000000000000 - 1 - cycleStart) % counting).toInt()

    var remainder = 0

    repeat(remainderTimes) {
        highestRock += blocked.nextStep(highestRock, shaper, jetStream).also { remainder += it }
    }

    return startHighestRock + cycleTimes * cycleHeight + remainder

}

data class JetStream(val input: String) {

    var current = 0

    fun blowsRight(): Boolean {
        if (current >= input.length) current = 0
        return input[current].also { current++ } == '>'
    }
}

data class Shape(var start: Position, var cells: List<Position>) {

    private var stopped = false
    private var blocked = emptySet<Position>()

    fun move(jetStream: JetStream, blocked: Set<Position>): List<Position> {
        this.blocked = blocked

        while (!stopped) {
            if (jetStream.blowsRight()) moveRight()
            else moveLeft()

            moveDown()
        }
        return cells
    }


    private fun moveRight() {
        if (cells.all { it.right().x < 8 && !blocked.contains(it.right()) }) cells = cells.map { it.right() }
    }

    private fun moveLeft() {
        if (cells.all { it.left().x > 0 && !blocked.contains(it.left()) }) cells = cells.map { it.left() }
    }

    private fun moveDown() {
        if (cells.all { it.down().y > 0 && !blocked.contains(it.down()) }) cells = cells.map { it.down() }
        else stopped = true
    }

}

class Shaper {
    var current = 0

    fun next(startAt: Position): Shape {
        if (current == 5) current = 0

        val nextShape = when (current) {
            0 -> Shape(startAt, horizontalLine(startAt))
            1 -> Shape(startAt, cross(startAt))
            2 -> Shape(startAt, reverseL(startAt))
            3 -> Shape(startAt, verticalLine(startAt))
            4 -> Shape(startAt, square(startAt))
            else -> throw Exception("Shape does not exist")
        }
        current++
        return nextShape
    }
}

data class Position(val x: Int, val y: Int) {
    fun right(amount: Int = 1) = Position(x + amount, y)
    fun left(amount: Int = 1) = Position(x - amount, y)
    fun down(amount: Int = 1) = Position(x, y - amount)
    fun up(amount: Int = 1) = Position(x, y + amount)
}

fun horizontalLine(start: Position) = listOf(start, start.right(), start.right(2), start.right(3))

fun cross(start: Position) = listOf(
    start.right(), start.up(), start.up().right(),
    start.up().right(2), start.up(2).right()
)

fun reverseL(start: Position) = listOf(
    start, start.right(), start.right(2),
    start.right(2).up(), start.right(2).up(2)
)

fun verticalLine(start: Position) = listOf(start, start.up(), start.up(2), start.up(3))

fun square(start: Position) = listOf(start, start.right(), start.up(), start.up().right())
