package aoc

import java.lang.Math.floorMod

fun solve20A(input: List<String>): Long {
    val numbers = input.mapIndexed { index, s -> Number(s.toLong(), index) }.toMutableList()

    numbers.mix()

    return numbers.extractGroveCoordinates()
}

fun solve20B(input: List<String>): Long {
    val decryptionKey = 811589153L
    val numbers = input.mapIndexed { index, s -> Number(s.toLong() * decryptionKey, index) }.toMutableList()

    repeat(10) { numbers.mix() }

    return numbers.extractGroveCoordinates()
}

data class Number(
    val value: Long,
    val originalPosition: Int,
)

fun MutableList<Number>.mix() {
    for (i in this.indices) {

        val current = this.find { it.originalPosition == i }!!
        val currentIndex = this.indexOf(current)

        if (current.value == 0L) continue

        val targetIndex = wrapIndex(current.value + currentIndex, this.size - 1)

        this.remove(current)
        this.add(targetIndex, current)
    }
}

fun wrapIndex(moveBy: Long, length: Int) = floorMod(moveBy, length)


fun List<Number>.extractGroveCoordinates(): Long {
    val zeroIndex = this.indexOfFirst { it.value == 0L }

    return listOf(1000L, 2000L, 3000L).sumOf {
        this[wrapIndex(
            it + zeroIndex,
            this.size
        )].value
    }
}
