package day10

import day10.OperationType.AFTER
import day10.OperationType.DURING

fun solveA(input: List<String>) = CycleTrackerB(listOf(20, 60, 100, 140, 180, 220))
    .apply { parseOperations(input, type = DURING) }.strengths.sum()

fun solveB(input: List<String>) = CycleTrackerB(listOf(41, 81, 121, 161, 201, 241))
    .apply { parseOperations(input, type = AFTER) }.message

enum class OperationType { DURING, AFTER }

data class CycleTrackerB(val relevantSignals: List<Int>) {
    private var cycles = 1
    private var register = 1
    val strengths = mutableListOf<Int>()
    var message = ""

    fun parseOperations(operations: List<String>, type: OperationType) = operations.forEach { op ->
        val amount = op.split(" ").last().toInt()
        when (op) {
            "noop" -> nextCycle()
            else -> if (type == DURING) addDuring(amount) else addAfter(amount)
        }
    }

    private fun addDuring(amount: Int) {
        nextCycle()
        addToRegister(amount)
        nextCycle()
    }

    private fun addAfter(amount: Int) {
        nextCycle()
        nextCycle()
        addToRegister(amount)
    }

    private fun addToRegister(amount: Int) {
        register += amount
    }

    private fun nextCycle() {
        message += if (isPixelInSprite()) "#" else "."

        cycles++

        if (cycles in relevantSignals) {
            message += "\n"
            strengths.add(cycles * register)
        }
    }

    private fun isPixelInSprite() = ((cycles - 1) % crtWidth) in register - 1..register + 1

    companion object {
        private const val crtWidth = 40
    }
}

//
////11m44s
//fun solveA(input: List<String>): Int {
//
//    val strengths: MutableList<Int> = mutableListOf()
//
//    val cycleTracker = CycleTracker(strengths)
//
//    input.forEach {
//        when {
//            it == "noop" -> cycleTracker.nextCycle()
//            else -> {
//                cycleTracker.nextCycle()
//                val add = it.split(" ").last().toInt()
//                cycleTracker.addToRegister(add)
//                cycleTracker.nextCycle()
//            }
//        }
//    }
//
//    return strengths.sum()
//}
//
//data class CycleTracker(val strengths: MutableList<Int>) {
//    private var cycles = 1
//    private var register = 1
//
//    private val relevantSignals = listOf(20, 60, 100, 140, 180, 220)
//
//
//    fun nextCycle() {
//        cycles++
//        if (cycles in relevantSignals) strengths.add(cycles * register)
//    }
//
//    fun addToRegister(amount: Int) {
//        register += amount
//    }
//}
//
//data class CycleTrackerB(val strengths: MutableList<Int>) {
//    private var cycles = 1
//    private var register = 1
//
//    private val relevantSignals = listOf(41, 81, 121, 161, 201, 241)
//
//
//    fun nextCycle() {
////        println("During Cycle $cycles")
////        println("CRT draws pixel in position ${cycles - 1} ")
//
//        if (((cycles - 1) % 40) in register - 1..register + 1) print("#")
//        else print(".")
//
//        cycles++
//
//        if (cycles in relevantSignals) {
//            println()
//            strengths.add(cycles * register)
//        }
////        println("Sprite position ${listOf(register - 1, register, register + 1)} ")
//    }
//
//    fun addToRegister(amount: Int) {
//        register += amount
//    }
//}
//
////27m55s
//
//fun solveB(input: List<String>): Int {
//    val strengths: MutableList<Int> = mutableListOf()
//
//    val cycleTracker = CycleTrackerB(strengths)
//
//    input.forEach {
//        when {
//            it == "noop" -> cycleTracker.nextCycle()
//            else -> {
//                cycleTracker.nextCycle()
//                cycleTracker.nextCycle()
//                val add = it.split(" ").last().toInt()
//                cycleTracker.addToRegister(add)
//            }
//        }
//    }
//
//    return strengths.sum()
//}
