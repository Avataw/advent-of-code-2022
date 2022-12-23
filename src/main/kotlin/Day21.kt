package aoc

fun solve21A(input: List<String>): Long {
    val monkeys = input.parse()

    val root = monkeys.first { it.name == "root" }

    monkeys.solveRiddle(root)

    return root.value!!
}

fun solve21B(input: List<String>): Long {

    val monkeys = input.parse()

    val root = monkeys.first { it.name == "root" }

    monkeys.solveRiddle(root)

    val firstValue = monkeys.getValue(root.operation!!.firstMonkeyName)!!
    val secondValue = monkeys.getValue(root.operation!!.secondMonkeyName)!!

    val firstOperation = monkeys.getFullOperation(root.operation!!.firstMonkeyName)
    val secondOperation = monkeys.getFullOperation(root.operation!!.secondMonkeyName)

    return if (firstOperation.length > secondOperation.length) solveEquation(firstOperation, secondValue)
    else solveEquation(secondOperation, firstValue)
}

fun List<Monkey>.solveRiddle(root: Monkey) {
    while (root.value == null) {

        val unsolvedMonkeys = this.filter { it.value == null && it.operation != null }

        for (monkey in unsolvedMonkeys) {
            val firstValue = this.first { it.name == monkey.operation!!.firstMonkeyName }.value
            val secondValue = this.first { it.name == monkey.operation!!.secondMonkeyName }.value

            if (firstValue == null || secondValue == null) continue

            monkey.value = calc(firstValue, secondValue, monkey.operation!!.operator)
        }
    }
}


fun solveEquation(operation: String, target: Long): Long {
    if (operation == "X") return target

    val removedSurrounding = operation.drop(1).dropLast(1)

    if (!removedSurrounding.contains('(') && !removedSurrounding.contains(')')) {
        return if (removedSurrounding[0] == 'X') tryCalc(removedSurrounding, target, reversed = true)
        else tryCalc(removedSurrounding, target)
    }

    val preOperation = removedSurrounding.takeWhile { it != '(' }
    val postOperation = removedSurrounding.takeLastWhile { it != ')' }

    return if (preOperation.isNotEmpty()) {
        val newTarget = tryCalc(preOperation, target)
        solveEquation(removedSurrounding.drop(preOperation.length), newTarget)
    } else {
        val newTarget = tryCalc(postOperation, target, reversed = true)
        solveEquation(removedSurrounding.dropLast(postOperation.length), newTarget)
    }
}

fun String.extractValue(operation: String, reversed: Boolean): Long {
    val split = this.split(operation)
    return (if (reversed) split.last() else split.first()).toLong()
}

fun tryCalc(input: String, target: Long, reversed: Boolean = false) = when {
    input.contains("+") -> calc(target, input.extractValue(" + ", reversed), '-')
    input.contains("/") -> calc(target, input.extractValue(" / ", reversed), '*')
    input.contains("*") -> calc(target, input.extractValue(" * ", reversed), '/')

    input.contains("-") -> {
        val value = input.extractValue(" - ", reversed)
        if (reversed) calc(target, value, '+') else calc(value, target, '-')
    }

    else -> throw Exception("Input $input could not be parsed")
}


fun List<Monkey>.getValue(name: String) = this.first { it.name == name }.value

fun List<Monkey>.getFullOperation(name: String): String {
    val monkey = this.first { it.name == name }
    val operation = monkey.operation

    if (monkey.name == "humn") return "X"
    if (operation == null) return monkey.value.toString()

    val left = this.getFullOperation(operation.firstMonkeyName)
    val right = this.getFullOperation(operation.secondMonkeyName)

    return if (!left.contains("X") && !right.contains("X")) monkey.value.toString()
    else "(" + left + " " + operation.operator + " " + right + ")"
}

fun calc(a: Long, b: Long, operator: Char) = when (operator) {
    '+' -> a + b
    '-' -> a - b
    '*' -> a * b
    '/' -> a / b
    else -> throw Exception("Operator $operator does not compute.")
}

fun List<String>.parse(): List<Monkey> = this.map {
    val split = it.split(": ")
    val name = split.first()
    val value = split.last().toLongOrNull()
    if (value == null) {
        val firstName = split.last().take(4)
        val secondName = split.last().takeLast(4)
        val operation = split.last()[5]
        Monkey(name, operation = Operation(firstName, secondName, operation))
    } else {
        Monkey(name, value)
    }
}

data class Monkey(val name: String, var value: Long? = null, var operation: Operation? = null)

data class Operation(val firstMonkeyName: String, val secondMonkeyName: String, var operator: Char)
