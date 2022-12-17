package day13

fun solveA(input: List<String>): Int {

    val pairs =
        input.chunked(3).map { signal ->
            Pair(Packet(signal[0]).also { it.initialize() },
                Packet(signal[1]).also { it.initialize() })
        }

    val result = pairs.mapIndexedNotNull { index, it ->
        if (it.first.compareTo(it.second) == 1) index + 1
        else null
    }

    return result.sum()
}

data class Packet(val input: String) : Comparable<Packet> {

    private val indexIsPacket = mutableListOf<Boolean>()
    private val packets = mutableListOf<Packet>()
    private val content = mutableListOf<Int>()
    private var current = -1

    private fun getNext(): Any? {
        current++
        if (current >= indexIsPacket.size) return null
        return if (indexIsPacket[current]) packets.removeFirstOrNull()
        else content.removeFirstOrNull()
    }

    fun initialize() {
        val inner = input.drop(1).dropLast(1)

        val openIndices = mutableListOf<Int>()
        val closeIndices = mutableListOf<Int>()

        for (i in inner.indices) {
            if (inner[i] == '[') openIndices.add(i)
            else if (inner[i] == ']') {
                if (openIndices.count() == closeIndices.count() + 1) {
                    val openIndex = openIndices.removeFirst()
                    indexIsPacket.add(true)
                    packets.add(Packet(inner.substring(openIndex, i + 1)).also { it.initialize() })
                } else closeIndices.add(i)
            } else if (openIndices.count() == closeIndices.count() && inner[i] != ',') {
                indexIsPacket.add(false)
                val digitString = inner.drop(i).takeWhile { it.isDigit() }
                if (digitString.isNotBlank()) content.add(digitString.toInt())
            }
        }

    }

    override fun compareTo(other: Packet): Int {

        val thisCopy = Packet(this.input).also { it.initialize() }
        val otherCopy = Packet(other.input).also { it.initialize() }

        while (true) {

            val p1 = thisCopy.getNext()
            val p2 = otherCopy.getNext()

            if (p1 == null && p2 != null) return 1
            if (p1 != null && p2 == null) return -1
            if (p1 == null) return 0

            if (p1 is Int && p2 is Int) {
                if (p1 < p2) return 1
                if (p1 > p2) return -1
            }

            if (p1 is Packet && p2 is Packet) {
                val innerResult = p1.compareTo(p2)
                if (innerResult != 0) return innerResult
            }

            if (p1 is Packet && p2 is Int) {
                val innerResult = p1.compareTo(Packet("[$p2]").also { it.initialize() })
                if (innerResult != 0) return innerResult
            }
            if (p1 is Int && p2 is Packet) {
                val innerResult = Packet("[$p1]").also { it.initialize() }.compareTo(p2)
                if (innerResult != 0) return innerResult
            }
        }
    }
}

fun solveB(input: List<String>): Int {
    val pairs =
        input.mapNotNull { signal ->
            if (signal.isNotBlank()) Packet(signal).also { it.initialize() }
            else null
        }.toMutableList()

    val firstDivider = Packet("[[2]]").also { it.initialize() }
    val secondDivider = Packet("[[6]]").also { it.initialize() }

    pairs.addAll(listOf(firstDivider, secondDivider))

    val sorted = pairs.sortedWith(compareBy<Packet> { it }.reversed().thenBy { it.input.length })

    return (sorted.indexOf(firstDivider) + 1) * (sorted.indexOf(secondDivider) + 1)
}