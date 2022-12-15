package day15

import kotlin.math.abs

fun solveA(input: List<String>, targetRow: Long): Int {
    val sensors = input.parseSensors()

    val beaconPositions = sensors.map { it.closestBeacon }.filter { it.y == targetRow }.toSet()

    val noBeaconAtLeftest = sensors.mapNotNull { it.noBeaconsAt(targetRow)?.first }.min()
    val noBeaconAtRightest = sensors.mapNotNull { it.noBeaconsAt(targetRow)?.second }.max()

    val beaconsInRange = beaconPositions.filter { it.x in noBeaconAtLeftest..noBeaconAtRightest }

    return (noBeaconAtLeftest..noBeaconAtRightest).count() - beaconsInRange.count()
}

fun solveB(input: List<String>, max: Long): Long? {
    val sensors = input.parseSensors()

    for (targetRow in 0..max) {
        val ranges = sensors.mapNotNull { it.noBeaconsAt(targetRow) }.map { it.first..it.second }

        ranges.forEach {
            val leftBorder = it.first - 1
            val rightBorder = it.last + 1

            if (leftBorder >= 0 && ranges.all { range -> !range.contains(leftBorder) }) return leftBorder * 4000000 + targetRow
            if (rightBorder <= max && ranges.all { range -> !range.contains(rightBorder) }) return rightBorder * 4000000 + targetRow
        }
    }

    return null
}

fun List<String>.parseSensors() = this.map {
    val beaconString = it.split(" at ").last()
    val beacon = beaconString.toPosition()
    val sensorString = it.drop(10).split(":").first()

    Sensor(sensorString.toPosition(), beacon)
}

fun String.toPosition(): Position {
    val input = this.split(", ")
    val x = input.first().split("=").last().toLong()
    val y = input.last().split("=").last().toLong()

    return Position(x, y)
}

data class Position(val x: Long, val y: Long) {

    fun distanceTo(other: Position) = abs(x - other.x) + abs(y - other.y)
}

data class Sensor(val position: Position, val closestBeacon: Position) {

    private val maxDistance = position.distanceTo(closestBeacon)

    fun noBeaconsAt(y: Long): Pair<Long, Long>? {
        val start = Position(position.x, y)

        val distanceToStart = position.distanceTo(start)
        if (distanceToStart > maxDistance) return null

        val diff = maxDistance - distanceToStart

        return Pair(position.x - diff, position.x + diff)
    }
}