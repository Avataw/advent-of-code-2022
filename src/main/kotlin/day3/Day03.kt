package day3


fun solveA(input: List<String>) = input
    .map { it.half() }
    .map { it.distinctLetters() }
    .sumOf { it.sumOf { c -> convertToPriority(c) } }

fun solveB(input: List<String>) = input
    .chunked(3)
    .map { it.distinctLetters() }
    .sumOf { it.sumOf { c -> convertToPriority(c) } }

fun convertToPriority(char: Char) = if (char.isUpperCase()) char.code - 38 else char.code - 96

fun String.toDistinct() = this.toSet().joinToString()

fun String.half() = this.chunked(this.length / 2)

fun List<String>.distinctLetters() = this.first()
    .filter { c -> this.all { s -> s.contains(c) } }
    .toDistinct()

// naive approach
//fun solveA(input: List<String>): Int {
//
//
//    val items = input.map {
//        val halfed = it.chunked(it.length / 2)
//        halfed.first().filter { inner -> halfed.last().contains(inner) }.toSet().joinToString()
//    }
//
//    return items.map { it.map { c -> convert(c) }.sum() }.sum()
//}
//
//fun convert(char: Char): Int {
//    return if (char.isUpperCase()) char.code - 38
//    else char.code - 96
//}
//
//fun solveB(input: List<String>): Int {
//
//    val groups = input.chunked(3)
//        .map { it[0].filter { inner -> it[1].contains(inner) && it[2].contains(inner) }.toSet().joinToString() }
//        .map { it.map { c -> convert(c) }.sum() }
//        .sum()
//    return groups
//}
