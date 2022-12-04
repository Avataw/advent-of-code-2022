package day4

fun solveA(input: List<String>) = input.count {
    val pair = convertToRanges(it)
    val intersection = pair.first.intersect(pair.second)
    intersection.size == pair.first.count() || intersection.size == pair.second.count()
}

fun solveB(input: List<String>) = input.count {
    val pair = convertToRanges(it)
    pair.first.intersect(pair.second).isNotEmpty()
}

fun convertToRanges(input: String): Pair<IntRange, IntRange> {
    val pair = input.split(",")
    val first = pair.first().split("-").map { it.toInt() }
    val firstRange = first.first()..first.last()
    val second = pair.last().split("-").map { it.toInt() }
    val secondRange = second.first()..second.last()

    return Pair(firstRange, secondRange)
}

// First and actually faster Approach
//fun solveA(input: List<String>) = input.count {
//    val pair = it.split(",")
//    val first = pair.first().split("-").map { digit -> digit.toInt() }
//    val second = pair.last().split("-").map { digit -> digit.toInt() }
//
//    if (first.first() <= second.first() && first.last() >= second.last()) true
//    else second.first() <= first.first() && second.last() >= first.last()
//}
//
//fun solveB(input: List<String>) = input.count {
//    val pair = it.split(",")
//    val first = pair.first().split("-").map { digit -> digit.toInt() }
//    val second = pair.last().split("-").map { digit -> digit.toInt() }
//
//    if (first.first() >= second.first() && first.first() <= second.last()) true
//    else if (first.last() >= second.first() && first.last() <= second.last()) true
//    else if (second.first() >= first.first() && second.first() <= first.last()) true
//    else second.last() >= first.first() && second.last() <= first.last()
//}
