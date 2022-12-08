package day8

fun solveA(input: List<String>): Int {

    val columns = input.getRows()

    return input.mapIndexed { y, line ->
        line.filterIndexed { x, tree ->
            val height = tree.digitToInt()
            findOutwardTrees(input, columns, x, y).any { it.checkVisibility(height) }
        }.length
    }.sum()
}

fun solveB(input: List<String>): Int {

    val columns = input.getRows()

    return input.mapIndexed { y, line ->
        line.mapIndexed { x, tree ->
            val height = tree.digitToInt()
            findOutwardTrees(input, columns, x, y).calcScenicScore(height)
        }
    }.flatten().max()
}

fun findOutwardTrees(rows: List<String>, columns: List<String>, x: Int, y: Int): List<List<Int>> {
    val left = rows[y].subSequence(0, x).reversed()
    val right = rows[y].subSequence(x + 1, rows[y].length)
    val up = columns[x].subSequence(0, y).reversed()
    val down = columns[x].subSequence(y + 1, columns.size)

    return listOf(left, right, up, down).map { it.map(Char::digitToInt) }
}

fun List<Int>.checkVisibility(height: Int): Boolean {
    val highestTreeInTheWay = this.maxOrNull() ?: return true
    return highestTreeInTheWay < height
}

fun List<String>.getRows(): List<String> {
    val columns = mutableListOf<String>()

    for (row in this) {
        for (i in row.indices) {
            if (columns.size <= i) columns.add("")
            columns[i] = columns[i].plus(row[i])
        }
    }

    return columns
}

fun List<List<Int>>.calcScenicScore(height: Int): Int = this.map { it.calcViewingDistance(height) }.reduce(Int::times)

fun List<Int>.calcViewingDistance(start: Int): Int = this
    .indexOfFirst { it >= start }
    .let {
        if (it == -1) this.size
        else it + 1
    }

//
//
////35:08
//fun solveA(input: List<String>): Int {
//
//    val trees = mutableListOf<Tree>()
//
//    val columns = input.getRows()
//
//    println(input)
//    println(columns)
//
//    for (y in input.indices) {
//        for (x in input[y].indices) {
//            val left = input[y].subSequence(0, x).map { it.digitToInt() }
//            val right = input[y].subSequence(x + 1, input[y].length).map { it.digitToInt() }
//            val up = columns[x].subSequence(0, y).map { it.digitToInt() }
//            val down = columns[x].subSequence(y + 1, input.size).map { it.digitToInt() }
//            trees.add(Tree(input[y][x].digitToInt(), left, right, up, down))
//        }
//    }
//
//    return trees.filter { it.isVisible() }.size
//}
//
//fun List<String>.getRows(): List<String> {
//    val columns = mutableListOf<String>()
//
//    for (i in this[0].indices) {
//        columns.add("")
//    }
//
//    for (row in this) {
//        for (i in row.indices) {
//            columns[i] = columns[i].plus(row[i])
//        }
//    }
//
//    return columns
//}
//
//fun solveB(input: List<String>): Int {
//
//    val trees = mutableListOf<Tree>()
//
//    val columns = input.getRows()
//
//    println(input)
//    println(columns)
//
//    for (y in input.indices) {
//        for (x in input[y].indices) {
//            val left = input[y].subSequence(0, x).map { it.digitToInt() }.reversed()
//            val right = input[y].subSequence(x + 1, input[y].length).map { it.digitToInt() }
//            val up = columns[x].subSequence(0, y).map { it.digitToInt() }.reversed()
//            val down = columns[x].subSequence(y + 1, input.size).map { it.digitToInt() }
//            trees.add(Tree(input[y][x].digitToInt(), left, right, up, down))
//        }
//    }
//
//    trees.forEach { println("$it ${it.scenicScore()}") }
//    return trees.maxOf { it.scenicScore() }
//}
//
//data class Tree(
//    val height: Int,
//    val left: List<Int>,
//    val right: List<Int>,
//    val up: List<Int>,
//    val down: List<Int>,
//) {
//    fun isVisible(): Boolean {
//        val visible = listOf(left, right, up, down).any { it.all { h -> h < height } }
//        println("Height: $height left $left right $right up $up down $down visible $visible")
//        return visible
//    }
//
//    fun scenicScore(): Int {
//
//        return listOf(left, right, up, down).map { calcViewingDistance(it, height) }.reduce(Int::times)
//    }
//}
//
////40:20
//fun calcViewingDistance(heights: List<Int>, start: Int): Int {
//
//    if (heights.isEmpty()) return 0
//
//    var distance = 0
//
//    for (height in heights) {
//        distance++
//        if (height >= start) break
//    }
//
//    println("$start: $distance for $heights ")
//    return distance
//}
