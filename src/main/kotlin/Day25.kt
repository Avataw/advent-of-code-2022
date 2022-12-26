package aoc

import kotlin.math.abs
import kotlin.math.pow

//2-=102--02--=1-12=22
fun solve25A(input: List<String>) = input.sumOf { it.fromSNAFU() }.toSNAFU()

fun String.fromSNAFU() = this.reversed().mapIndexed { index, c ->
    val fiveSquared = 5.0.pow(index)

    when (c) {
        '2' -> fiveSquared * 2
        '1' -> fiveSquared
        '0' -> 0.0
        '-' -> fiveSquared * -1
        '=' -> fiveSquared * -2
        else -> throw Exception("$c could not be parsed.")
    }
}.sum().toLong()


fun Long.toSNAFU(): String {
    var exponent = 0
    while (5.0.pow(exponent).toLong() < this && 5.0.pow(exponent).toLong() * 2 < this) {
        exponent++
    }

    return this.toSNAFU(exponent)
}

fun Long.toSNAFU(exp: Int): String {

    if (exp == -1) return ""

    if (this == 0L) return "0" + 0L.toSNAFU(exp - 1)

    val fiveSquared = 5.0.pow(exp).toLong()

    val isNegative = this < 0

    val first = if (isNegative) fiveSquared * -1 else fiveSquared
    val second = if (isNegative) fiveSquared * -2 else fiveSquared * 2

    val currentRemainder = abs(this)
    val firstRemainder = abs(this - first)
    val secondRemainder = abs(this - second)

    if (firstRemainder > currentRemainder && secondRemainder > currentRemainder) return "0" + this.toSNAFU(exp - 1)

    return if (firstRemainder < secondRemainder) {
        val remainder = this - first
        val result = if (isNegative) "-" else "1"
        result + remainder.toSNAFU(exp - 1)
    } else {
        val remainder = this - second
        val result = if (isNegative) "=" else "2"
        result + remainder.toSNAFU(exp - 1)
    }
}
