package day5

fun solveA(input: List<String>): String {

    val stacks = input.filterStackLines().parseToStack()

    input.filterOperationLines().parseOperations().runOperations(stacks, oneByOne = true)

    return stacks.getTopValues()
}

fun solveB(input: List<String>): String {

    val stacks = input.filterStackLines().parseToStack()

    input.filterOperationLines().parseOperations().runOperations(stacks, oneByOne = false)

    return stacks.getTopValues()
}

fun List<String>.filterStackLines() = this.takeWhile { !it.startsWith(" 1") }

fun List<String>.filterOperationLines() = this.filter { it.startsWith("move") }

fun List<String>.parseToStack(): MutableMap<Int, String> {
    val stacks = mutableMapOf<Int, String>()

    this.forEach {
        it.chunked(4).forEachIndexed { index, s ->
            if (s.isNotBlank()) {
                val existing = stacks[index + 1] ?: ""
                stacks[index + 1] = existing + s[1]
            }
        }
    }

    return stacks
}

fun List<String>.parseOperations(): List<List<Int>> =
    this.map { it.split(" ").mapNotNull { op -> op.toIntOrNull() } }

fun List<List<Int>>.runOperations(stacks: MutableMap<Int, String>, oneByOne: Boolean = true) = this.forEach {
    val (amount, from, to) = it

    val str = stacks[from]?.take(amount) ?: throw Exception("Stack $from does not exist!")
    stacks[from] = stacks[from]?.drop(amount) ?: throw Exception("Stack $from does not exist!")
    stacks[to] = if (oneByOne) str.reversed() + stacks[to] else str + stacks[to]
}

fun Map<Int, String>.getTopValues() = this.toSortedMap().map { it.value.first() }.joinToString("")

// First Naive approach with "hardcoded" parsing
//fun solveA(input: List<String>): String {
//
//    val stacks = mutableMapOf<Int, String>()
//
//    val stackLines = 8
//    val inputLines = 10
//
//    val lines = input.filterIndexed { index, _ -> index < stackLines }
//
//    println(lines)
//
//    for (line in lines) {
//        var stack = 1
//        var start = 0
//        var end = 3
//
//        while (end <= line.length) {
//            val name = line.subSequence(start, end)
//            if (name.isNotEmpty() && name.isNotBlank()) {
//                val existing = stacks[stack] ?: ""
//                stacks[stack] = existing + name[1]
//            }
//            start += 4
//            end += 4
//            stack++
//        }
//    }
//
//    val operations = input.filterIndexed { index, s -> index >= inputLines }
//
//    println(operations)
//
////    println("START ${stacks.toSortedMap()}")
//
//    for (operation in operations) {
//        val op = operation.split(" ")
//
//        val amount = op[1].toInt()
//        val from = op[3].toInt()
//        val to = op[5].toInt()
//
//
//        val str = stacks[from]?.take(amount) ?: return "?"
//        stacks[from] = stacks[from]?.drop(amount) ?: return "?"
//        stacks[to] = str.reversed() + stacks[to]
//
////        println(operation + " ${stacks.toSortedMap()}")
//
//    }
//
//
////    println("END ${stacks.toSortedMap()}")
//
//    return stacks.toSortedMap().map { it.value.first() }.joinToString("")
//}
//
//fun solveB(input: List<String>): String {
//
//    val stacks = mutableMapOf<Int, String>()
//
//    val stackLines = 8
//    val inputLines = 10
//
//    val lines = input.filterIndexed { index, _ -> index < stackLines }
//
//    println(lines)
//
//    for (line in lines) {
//        var stack = 1
//        var start = 0
//        var end = 3
//
//        while (end <= line.length) {
//            val name = line.subSequence(start, end)
//            if (name.isNotEmpty() && name.isNotBlank()) {
//                val existing = stacks[stack] ?: ""
//                stacks[stack] = existing + name[1]
//            }
//            start += 4
//            end += 4
//            stack++
//        }
//    }
//
//    val operations = input.filterIndexed { index, s -> index >= inputLines }
//
//    println(operations)
//
////    println("START ${stacks.toSortedMap()}")
//
//    for (operation in operations) {
//        val op = operation.split(" ")
//
//        val amount = op[1].toInt()
//        val from = op[3].toInt()
//        val to = op[5].toInt()
//
//
//        val str = stacks[from]?.take(amount) ?: return "?"
//        stacks[from] = stacks[from]?.drop(amount) ?: return "?"
//        stacks[to] = str + stacks[to]
//
////        println(operation + " ${stacks.toSortedMap()}")
//
//    }
//
//
////    println("END ${stacks.toSortedMap()}")
//
//    return stacks.toSortedMap().map { it.value.first() }.joinToString("")
//}
