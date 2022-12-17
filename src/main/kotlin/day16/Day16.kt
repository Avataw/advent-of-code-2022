package day16

import kotlin.math.ceil

fun solveA(input: List<String>): Int {

    val valves = input.map(String::parseValve)

//    valves.forEach { println(it) }

    valves.calculateShortestPaths()
//
//    valves.forEach {
//        println("${it.name} : ${it.shortestPathTo.map { it.key.name to it.value }}")
//    }

    var currentPosition = valves.find { it.name == "AA" }!!
    var nextMove: Valve? = null
    var distance = 0

    var flowSum = 0

    for (times in 0 until 30) {
        println("== Minute ${times + 1} == ")
        println("Valves ${valves.count { it.open }} are open, releasing ${
            valves.filter { it.open }.sumOf { it.flow }
        } pressure.")
        println("Currently at ${currentPosition.name} distance $distance")

        flowSum += valves.filter { it.open }.sumOf { it.flow }

        if (distance > 0) {
            distance--
            println()
            continue
        }

        if (nextMove != null) {
            currentPosition = nextMove
            println("You open valve ${currentPosition.name}.")
            println()
            currentPosition.open = true
            nextMove = null
            continue
        }

        val closest = valves.filter { v -> v.name != currentPosition.name }.filter { v -> !v.open && v.flow > 0 }
            .sortedWith(compareByDescending<Valve> { v -> ceil(v.flow.toFloat() / v.shortestPathTo[currentPosition.name]!!.toFloat()) }.thenBy { v -> v.shortestPathTo[currentPosition.name] })

        println("CLOSEST ${closest.map { it.name }}")
        nextMove = closest.firstOrNull() ?: continue
        distance = nextMove.shortestPathTo[currentPosition.name]!! - 1

        println("You move to valve $nextMove")
        println()
    }

    return flowSum
}

fun List<Valve>.calculateShortestPaths() = this.forEach { valve ->
    var path = 0

    this.forEach {
        valve.shortestPathTo[it.name] = Int.MAX_VALUE
    }
    valve.shortestPathTo[valve.name] = 0

    var currentTunnels = valve.tunnelsTo.toValves(this)

    while (currentTunnels.isNotEmpty()) {
        val next = mutableListOf<Valve>()
        currentTunnels.forEach {
            if (valve.shortestPathTo[it.name]!! > path + 1) {
                valve.shortestPathTo[it.name] = path + 1
                next.addAll(it.tunnelsTo.toValves(this))
            }
        }
        currentTunnels = next
        path++
    }
}


fun List<String>.toValves(valves: List<Valve>) = this.map { name -> valves.find { it.name == name }!! }


//2980
fun solveB(input: List<String>): Int {
    val valves = input.map(String::parseValve)

    valves.calculateShortestPaths()


    var maximum = 0

    for (i in 0..1000000) {

        var flowSum = 0

        valves.forEach {
            it.open = false
            it.targeted = false
        }

        val e1 = Elephant("Elephant 1", valves)
        val e2 = Elephant("Elephant 2", valves)

        for (times in 0 until 26) {

            flowSum += valves.filter { it.open }.sumOf { it.flow }

            e1.step()
            e2.step()
        }

        if (flowSum > maximum) maximum = flowSum
    }

    return maximum
}

data class Elephant(val name: String, val valves: List<Valve>) {

    private var currentPosition = valves.find { it.name == "AA" }!!
    private var nextMove: Valve? = null
    private var distance = 0

    fun step() {
        if (distance > 0) {
            distance--
            return
        }

        if (nextMove != null) {
            currentPosition = nextMove!!
            currentPosition.open = true
            nextMove = null
            return
        }

        val closest = valves
            .filter { v -> v.name != currentPosition.name }
            .filter { v -> !v.targeted }
            .filter { v -> !v.open && v.flow > 0 }
            .sortedWith(compareByDescending<Valve> { v -> ceil(v.flow.toFloat() / v.shortestPathTo[currentPosition.name]!!.toFloat()) }.thenBy { v -> v.shortestPathTo[currentPosition.name] })

        if (closest.isEmpty()) return

        nextMove = closest.take(4).shuffled().first()
        nextMove!!.targeted = true
        distance = nextMove!!.shortestPathTo[currentPosition.name]!! - 1
    }
}

fun String.parseValve(): Valve {
    val split = this.split("; ")

    val tunnels = split.last().split(" ").drop(4).joinToString("").split(",")
    val name = split.first().split(" ")[1]
    val flow = split.first().split(" ").last().split("=").last()
    return Valve(name, flow.toInt(), tunnels)
}


data class Valve(val name: String, val flow: Int, val tunnelsTo: List<String>, var open: Boolean = false) {
    val shortestPathTo = mutableMapOf<String, Int>()
    var targeted = false
}

