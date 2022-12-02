package day2

val outcomesA = mapOf(
    "A X" to 4, "A Y" to 8, "A Z" to 3,
    "B X" to 1, "B Y" to 5, "B Z" to 9,
    "C X" to 7, "C Y" to 2, "C Z" to 6,
)

fun solveA(input: List<String>) = input.mapNotNull { outcomesA[it] }.sum()

val outcomesB = mapOf(
    "A X" to 3, "A Y" to 4, "A Z" to 8,
    "B X" to 1, "B Y" to 5, "B Z" to 9,
    "C X" to 2, "C Y" to 6, "C Z" to 7,
)

fun solveB(input: List<String>) = input.mapNotNull { outcomesB[it] }.sum()

// Quick and Dirty Naive solution:
//
//fun solveA(input: List<String>): Int {
//    var sum = 0
//
//    input.map {
//        val line = it.split(" ")
//        when (line.first()) {
//            "A" -> {
//                when (line.last()) {
//                    "X" -> sum += 4
//                    "Y" -> sum += 8
//                    "Z" -> sum += 3
//                }
//            }
//
//            "B" -> {
//                when (line.last()) {
//                    "X" -> sum += 1
//                    "Y" -> sum += 5
//                    "Z" -> sum += 9
//                }
//            }
//
//            "C" -> {
//                when (line.last()) {
//                    "X" -> sum += 7
//                    "Y" -> sum += 2
//                    "Z" -> sum += 6
//                }
//            }
//        }
//
//    }
//
//    return sum
//}
//
//fun solveB(input: List<String>): Int {
//    var sum = 0
//
//    input.map {
//        val line = it.split(" ")
//        when (line.first()) {
//            "A" -> {
//                when (line.last()) {
//                    "X" -> sum += 3
//                    "Y" -> sum += 4
//                    "Z" -> sum += 8
//                }
//            }
//
//            "B" -> {
//                when (line.last()) {
//                    "X" -> sum += 1
//                    "Y" -> sum += 5
//                    "Z" -> sum += 9
//                }
//            }
//
//            "C" -> {
//                when (line.last()) {
//                    "X" -> sum += 2
//                    "Y" -> sum += 6
//                    "Z" -> sum += 7
//                }
//            }
//        }
//
//    }
//
//    return sum
//}

