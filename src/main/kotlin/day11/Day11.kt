package day11

// 36:12
fun solveA(input: List<String>) = parseMonkeys(input).also {
    repeat(20) { _ ->
        it.forEach { m -> m.takeTurn(it) }
    }
}.calcMonkeyBusiness()

//13:32
fun solveB(input: List<String>) = parseMonkeys(input).also {
    val superModulo = it.map { monkey -> monkey.test.divisibleBy }.reduce(Long::times)

    repeat(10000) { _ ->
        it.forEach { m -> m.takeTurn(it, superModulo) }
    }
}.calcMonkeyBusiness()

fun parseMonkeys(input: List<String>) = input
    .chunked(7)
    .mapIndexed { id, strings ->
        val startingItems = strings[1].split(" ").drop(4).joinToString("").split(",").map { it.toLong() }
        val inspection = Inspection(strings[2].split(" ").drop(6))
        val test = Test(listOf(strings[3], strings[4], strings[5]).map { it.split(" ").last() })

        Monkey(id, startingItems.toMutableList(), inspection, test)
    }

data class Inspection(val operator: String, val operationValue: Long?) {
    constructor(input: List<String>) : this(input.first(), input.last().toLongOrNull())

    fun run(worryLevel: Long): Long {
        val value = operationValue ?: worryLevel
        return if (operator == "*") worryLevel * value else worryLevel + value
    }
}

data class Test(val divisibleBy: Long, val trueTarget: Int, val falseTarget: Int) {
    constructor(input: List<String>) : this(input[0].toLong(), input[1].toInt(), input[2].toInt())

    fun run(worryLevel: Long) = if (worryLevel % divisibleBy == 0L) trueTarget else falseTarget
}

data class Monkey(
    val id: Int,
    var items: MutableList<Long>,
    val inspection: Inspection,
    val test: Test,
) {
    var inspections = 0
    fun takeTurn(monkeys: List<Monkey>, superModulo: Long? = null) = items.forEach {
        inspections++
        val afterInspection = inspection.run(it)
        val result = if (superModulo == null) afterInspection.floorDiv(3) else afterInspection % superModulo
        val target = test.run(result)
        monkeys[target].items.add(result)
    }.also { items.clear() }
}

fun List<Monkey>.calcMonkeyBusiness() = this.map { it.inspections.toLong() }.sortedDescending().let { it[0] * it[1] }

//
//
//var superMod = mutableListOf<Int>()
//
//// 36:12
//fun solveA(input: List<String>): Long {
//
//    val monkeys = parseMonkeys(input, isPartOne = true)
//
//    repeat(20) {
//        monkeys.forEach { m -> m.round(monkeys) }
//    }
//
//    val inspections = monkeys.map { it.inspections.toLong() }.sortedDescending()
//    return inspections.first() * inspections[1]
//}
//
//fun parseMonkeys(input: List<String>, isPartOne: Boolean): List<Monkey> {
//    val parseMonkeys = input.chunked(7)
//
//    return parseMonkeys.mapIndexed { id, strings ->
//        val startingItems = strings[1].split(" ").drop(4).joinToString("").split(",").map { it.toLong() }
//        val inspectionInput = strings[2].split(" ").drop(6)
//        val inspectionFn = parseInspection(inspectionInput.first(), inspectionInput.last())
//        val divisibleBy = strings[3].split(" ").last()
//        if (!isPartOne) superMod.add(divisibleBy.toInt())
//        val trueTarget = strings[4].split(" ").last()
//        val falseTarget = strings[5].split(" ").last()
//        val testFn = parseTest(divisibleBy, trueTarget, falseTarget)
//        Monkey(id, startingItems.toMutableList(), inspectionFn, testFn, isPartOne)
//    }
//}
//
//fun parseInspection(operator: String, byString: String): (Long) -> Long {
//
//    val isMultiply = operator == "*"
//
//    val function: (old: Long) -> Long = {
//        val by = byString.toLongOrNull() ?: it
//        if (isMultiply) it * by
//        else it + by
//    }
//
//    return function
//}
//
//fun parseTest(divisibleBy: String, trueTarget: String, falseTarget: String): (Long) -> Int {
//    val function: (old: Long) -> Int = {
//        if (it % divisibleBy.toLong() == 0L) trueTarget.toInt()
//        else falseTarget.toInt()
//    }
//
//    return function
//}
//
//
////13:32
//fun solveB(input: List<String>): Long {
//    val monkeys = parseMonkeys(input, isPartOne = false)
//
//    repeat(10000) {
//        monkeys.forEach { m -> m.round(monkeys) }
//    }
//
//    val inspections = monkeys.map { it.inspections.toLong() }.sortedDescending()
//    return inspections.first() * inspections[1]
//}
//
//data class Monkey(
//    val id: Int,
//    var items: MutableList<Long>,
//    val inspectionFn: (Long) -> Long,
//    val testFn: (Long) -> Int,
//    val isPartOne: Boolean
//) {
//
//    var inspections = 0
//    fun round(monkeys: List<Monkey>) {
//        items.forEach {
//            inspections++
//            val afterInspection = inspectionFn(it)
//            val result = if (isPartOne) afterInspection.floorDiv(3) else afterInspection % superMod.reduce(Int::times)
//            val target = testFn(result)
//            monkeys[target].items.add(result)
//        }
//        items.clear()
//    }
//}
