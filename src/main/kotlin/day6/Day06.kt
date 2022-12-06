package day6

fun solveA(input: List<String>) = input.map { findMessageStart(it, 4) }

fun solveB(input: List<String>): List<Int> = input.map { findMessageStart(it, 14) }

fun findMessageStart(signal: String, messageLength: Int): Int {
    for (i in signal.indices) {
        val peek = signal.subSequence(i, i + messageLength)
        if (peek.toSet().size == messageLength) return i + messageLength
    }

    throw Exception("No start of message exists!")
}

// first approach
////9:17
//fun solveA(input: List<String>): List<Int> {
//
//    val results = mutableListOf<Int>()
//
//    input.map {
//        for (i in it.indices) {
//            val peek = it.subSequence(i, i + 4)
//            if (peek.toSet().size == 4) {
//                results.add(i + 4)
//                break
//            }
//        }
//    }
//
//    return results
//}
//
//
////3:06
//fun solveB(input: List<String>): List<Int> {
//
//    val results = mutableListOf<Int>()
//
//    input.map {
//        for (i in it.indices) {
//            val peek = it.subSequence(i, i + 14)
//            if (peek.toSet().size == 14) {
//                results.add(i + 14)
//                break
//            }
//        }
//    }
//
//    return results
//}
