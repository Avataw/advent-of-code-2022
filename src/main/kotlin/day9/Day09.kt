package day9

//35:30min
fun solveA(input: List<String>): Int {
    val tail = Knot("T", tail = null)
    val head = Knot("H", tail = tail)

    input.forEach { movement ->
        val (direction, amountString) = movement.split(" ")
        val amount = amountString.toInt()
        repeat(amount) { head.move(direction) }
    }

    return tail.visited.toSet().count()
}

// 63:21min
fun solveB(input: List<String>): Int {
    val finalTail = Knot("9", tail = null)

    val head = (8 downTo 0).fold(finalTail) { prevKnot, next: Int ->
        Knot(next.toString(), tail = prevKnot)
    }

    input.forEach { movement ->
        val (direction, amountString) = movement.split(" ")
        val amount = amountString.toInt()
        repeat(amount) { head.move(direction) }
    }

    return finalTail.visited.toSet().count()
}

data class Position(val x: Int, val y: Int) {

    fun up() = Position(x, y + 1)
    fun down() = Position(x, y - 1)
    fun left() = Position(x - 1, y)
    fun right() = Position(x + 1, y)

    fun downRight() = down().right()
    fun downLeft() = down().left()
    fun upRight() = up().right()
    fun upLeft() = up().left()

    fun isAdjacent(other: Position) = other.x in x - 1..x + 1 && other.y in y - 1..y + 1

    fun isRightFrom(other: Position) = x > other.x
    fun isLeftFrom(other: Position) = x < other.x
    fun isUpFrom(other: Position) = y > other.y
    fun isDownFrom(other: Position) = y < other.y
    fun isSameRowAs(other: Position) = y == other.y
    fun isSameColumnAs(other: Position) = x == other.x
}

data class Knot(val name: String, var position: Position = Position(0, 0), val tail: Knot?) {

    val visited = mutableListOf(position)

    fun move(direction: String) {

        val newPosition: Position = when (direction) {
            "U" -> position.up()
            "D" -> position.down()
            "R" -> position.right()
            "L" -> position.left()
            else -> throw Exception("Position could not be parsed")
        }
        visited.add(newPosition).also {
            position = newPosition
            tail?.trailBehind(position)
        }
    }

    private fun trailBehind(other: Position) {
        if (position.isAdjacent(other)) return

        val newPosition = when {
            position.isDownFrom(other) && position.isSameColumnAs(other) -> position.up()
            position.isUpFrom(other) && position.isSameColumnAs(other) -> position.down()
            position.isLeftFrom(other) && position.isSameRowAs(other) -> position.right()
            position.isRightFrom(other) && position.isSameRowAs(other) -> position.left()
            position.isDownFrom(other) && position.isLeftFrom(other) -> position.upRight()
            position.isDownFrom(other) && position.isRightFrom(other) -> position.upLeft()
            position.isUpFrom(other) && position.isLeftFrom(other) -> position.downRight()
            position.isUpFrom(other) && position.isRightFrom(other) -> position.downLeft()
            else -> throw Exception("Next position could not be calculated ")
        }

        visited.add(newPosition).also {
            position = newPosition
            tail?.trailBehind(position)
        }
    }
}

// party B
//package day9
//
////35:30
//fun solveA(input: List<String>): Int {
//    val tail = Knot("T", tail = null)
//    val head = Knot("H", tail = tail)
//
//    for (movement in input) {
//        val (direction, amountString) = movement.split(" ")
//        println("$direction $amountString")
//        val amount = amountString.toInt()
//
//        for (step in 0 until amount) {
//            head.move(direction)
//        }
//    }
//
//    return head.tail!!.visited.toSet().count()
//}
//
//// 63:21min
//fun solveB(input: List<String>): Int {
//    val nine = Knot("9", tail = null)
//    val eight = Knot("8", tail = nine)
//    val seven = Knot("7", tail = eight)
//    val six = Knot("6", tail = seven)
//    val five = Knot("5", tail = six)
//    val four = Knot("4", tail = five)
//    val three = Knot("3", tail = four)
//    val two = Knot("2", tail = three)
//    val one = Knot("1", tail = two)
//    val head = Knot("H", tail = one)
//
//
//    for (movement in input) {
//        val (direction, amountString) = movement.split(" ")
//        println("$direction $amountString")
//        val amount = amountString.toInt()
//
//        for (step in 0 until amount) {
//            head.move(direction)
//        }
//        println("-> <-")
//        println(nine)
//    }
//
//    return nine.visited.toSet().count()
//}
//
//
//// same row same column, just
//
//data class Position(val x: Int, val y: Int)
//
//
//data class Knot(val name: String, var position: Position = Position(0, 0), val tail: Knot?) {
//
//    val visited = mutableListOf(position)
//
//    private fun isTouching(other: Knot) = position.x in other.position.x - 1..other.position.x + 1 &&
//            position.y in other.position.y - 1..other.position.y + 1
//
//    fun move(direction: String) {
//
//        var newPosition: Position = when (direction) {
//            "U" -> Position(position.x, position.y + 1)
//            "D" -> Position(position.x, position.y - 1)
//            "R" -> Position(position.x + 1, position.y)
//            "L" -> Position(position.x - 1, position.y)
//            else -> throw Exception("Position could not be parsed")
//        }
//        visited.add(newPosition)
//
//        println("Move $name from $position to $newPosition")
//        position = newPosition
//        trailBehind()
//    }
//
//    private fun trailBehind() {
//        if (tail == null) return
//        if (isTouching(tail)) return
//
//        var newPosition: Position? = null
//
//        if (position.x > tail.position.x && position.y == tail.position.y) {
//            newPosition = Position(tail.position.x + 1, tail.position.y)
//        } else if (position.x < tail.position.x && position.y == tail.position.y) {
//            newPosition = Position(tail.position.x - 1, tail.position.y)
//        } else if (position.x == tail.position.x && position.y > tail.position.y) {
//            newPosition = Position(tail.position.x, tail.position.y + 1)
//        } else if (position.x == tail.position.x && position.y < tail.position.y) {
//            newPosition = Position(tail.position.x, tail.position.y - 1)
//        } else if (position.x > tail.position.x && position.y > tail.position.y) {
//            newPosition = Position(tail.position.x + 1, tail.position.y + 1)
//        } else if (position.x > tail.position.x && position.y < tail.position.y) {
//            newPosition = Position(tail.position.x + 1, tail.position.y - 1)
//        } else if (position.x < tail.position.x && position.y > tail.position.y) {
//            newPosition = Position(tail.position.x - 1, tail.position.y + 1)
//        } else if (position.x < tail.position.x && position.y < tail.position.y) {
//            newPosition = Position(tail.position.x - 1, tail.position.y - 1)
//        }
//
//        if (newPosition == null) throw Exception("something went wrong")
//
//        tail.visited.add(newPosition)
//        tail.position = newPosition
//        tail.trailBehind()
//    }
//}


//package day9 Part A
//
////35:30
//fun solveA(input: List<String>): Int {
//    val head = Knot("H", Position(0, 0))
//    val tail = Knot("T", Position(0, 0))
//
//    for (movement in input) {
//        val (direction, amountString) = movement.split(" ")
//        println("$direction $amountString")
//        val amount = amountString.toInt()
//
//        for (step in 0 until amount) {
//            head.move(direction)
//            if (head.isTouching(tail)) continue
//
//            if (head.position.x == tail.position.x || head.position.y == tail.position.y) {
//                tail.move(direction)
//            } else {
//                if (direction == "R" || direction == "L") {
//                    if (head.position.y > tail.position.y) tail.move("U$direction")
//                    else tail.move("D$direction")
//                } else {
//                    if (head.position.x > tail.position.x) tail.move(direction + "R")
//                    else tail.move(direction + "L")
//                }
//            }
//        }
//    }
//
//    return tail.visited.toSet().count()
//}
//
//fun solveB(input: List<String>): Long {
//    TODO()
//}
//
//
//// same row same column, just
//
//data class Position(val x: Int, val y: Int)
//
//data class Knot(val name: String, var position: Position) {
//
//    val visited = mutableListOf(position)
//
//    fun isTouching(other: Knot) = position.x in other.position.x - 1..other.position.x + 1 &&
//            position.y in other.position.y - 1..other.position.y + 1
//
//    fun move(direction: String) {
//        var newPosition: Position?
//
//        when (direction) {
//            "U" -> newPosition = Position(position.x, position.y + 1)
//            "D" -> newPosition = Position(position.x, position.y - 1)
//            "R" -> newPosition = Position(position.x + 1, position.y)
//            "L" -> newPosition = Position(position.x - 1, position.y)
//            "UR" -> newPosition = Position(position.x + 1, position.y + 1)
//            "UL" -> newPosition = Position(position.x - 1, position.y + 1)
//            "DR" -> newPosition = Position(position.x + 1, position.y - 1)
//            "DL" -> newPosition = Position(position.x - 1, position.y - 1)
//            else -> throw Exception("Position could not be parsed")
//        }
//        visited.add(newPosition)
//
//        println("Move $name from $position to $newPosition")
//        position = newPosition
//    }
//
//}
//
//
