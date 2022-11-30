package day1

fun solveA(input: List<String>): Int = input
    .chunkByEmptyLine()
    .reduceToSum()
    .getSumOfNHighestSums(1)


fun solveB(input: List<String>): Int = input
    .chunkByEmptyLine()
    .reduceToSum()
    .getSumOfNHighestSums(3)

fun List<String>.chunkByEmptyLine() = this
    .joinToString(" ") { it.ifEmpty { "," } }
    .split(" , ")
    .map { it.split(" ") }

fun List<String>.mapToInt() = this.map { it.toInt() }

fun List<List<String>>.reduceToSum() = this.map { it.mapToInt().sum() }

fun List<Int>.getSumOfNHighestSums(n: Int) = this.sortedDescending().take(n).sum()

