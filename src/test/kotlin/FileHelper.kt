import java.io.File

fun readInput(day: Int): List<String>
        = File("src/test/resources/day${day}/input.txt").useLines { it.toList() }

fun readTestInput(day: Int): List<String>
        = File("src/test/resources/day${day}/test.txt").useLines { it.toList() }