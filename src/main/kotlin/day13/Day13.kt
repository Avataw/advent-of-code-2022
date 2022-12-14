package day13

fun solveA(input: List<String>) {
//
//    val pairs =
//        input.chunked(3).map { signal ->
//            Pair(Packet(signal[0]).also { it.initialize() },
//                Packet(signal[1]).also { it.initialize() })
//        }
//
//    var indices = 0
//
//    outer@ for(pair in pairs) {
//        for (index in pair.first.indexIsPacket.indices) {
//
//            if(pair.second.indexIsPacket.count() <= index) continue@outer
//
//            if(!pair.first.indexIsPacket[index] && !pair.second.indexIsPacket[index]) {
//                if
//            }
//
//            if(!.first.indexIsPacket[index])
//        }
//
//    }
//
//
//    pairs.forEach {
//        println(it.first)
//        println(it.first.indexIsPacket)
//
//        for (index in it.first.indexIsPacket.indices) {
//
//            if(!it.first.indexIsPacket[index])
//        }
//
//        println(it.second)
//        println(it.second.indexIsPacket)
//    }
//
//
//    println(pairs)
    TODO()
}
//
//fun Pair<Packet, Packet>.validate() : Int? {
//
//    for(i in this.first.indexIsPacket.indices) {
//        val first = this.first.getNext()
//        val second = this.second.getNext()
//
//        if(first == null && second != null) return 1
//        if(first != null && second == null) return null
//        if(first == null && second == null) throw Exception("not supposed to happen?")
//
//        if(first is Int && second is Int) {
//            if(first < second) return 1
//            if(second > first) return null
//            if(first == second) continue
//        }
//
//        if(first is Int && second is Packet) {
//
//            var secondInput = second.content
//
//            if(first < second.content.first())
//            if(second.content)
//        }
//
//
//        if(first is Packet && second is Packet) {
//            return Pair(first,second).validate()
//        }
//    }
//}
//
//fun compare(firstPacket: Packet, secondPacket:Packet) : Int? {
//    val first = firstPacket.getNext()
//    val second = secondPacket.getNext()
//
//    if(first == null && second != null) return 1
//    if(first != null && second == null) return null
//    if(first == null && second == null) throw Exception("not supposed to happen?")
//
//    if(first is Int && second is Int) {
//        if(first < second) return 1
//        if(second > first) return 0
//        if(first == second) return null
//    }
//
//    if(first is Int && second is Packet) return compare(Packet("[$first]"), second)
//
//    if(first is Packet && second is Int) return compare(first, Packet("[$second]"))
//
//    if(first is Packet && second is Packet) {
//        if(first.content.isNotEmpty() && second.content.isNotEmpty()) {
//            for(i in first.content.indices) {
//                if(i >= second.content.count()) return 0
//                if(first.content[i] < second.content[i]) return 1
//                if(first.content[i] > second.content[i]) return 0
//            }
//         } else if(first.content)
//
//    }
//
//    return null
//}
//
//data class Packet(val input: String) {
//
//    val indexIsPacket = mutableListOf<Boolean>()
//    val packets = mutableListOf<Packet>()
//    val content = mutableListOf<Int>()
//    var current = -1
//
//
//    fun getNext(): Any? {
//        current++
//        return if(indexIsPacket[current]) packets.removeFirst()
//        else content.removeFirst()
//    }
//
//
//    fun initialize() {
//        val inner = input.drop(1).dropLast(1)
//
//        val openIndices = mutableListOf<Int>()
//        val closeIndices = mutableListOf<Int>()
//
//        for (i in inner.indices) {
//            if (inner[i] == '[') openIndices.add(i)
//            else if (inner[i] == ']') {
//                if (openIndices.count() == closeIndices.count() + 1) {
//                    val openIndex = openIndices.removeFirst()
//                    indexIsPacket.add(true)
//                    packets.add(Packet(inner.substring(openIndex, i + 1)).also { it.initialize() })
//                } else closeIndices.add(i)
//            } else if (openIndices.isEmpty() && inner[i] != ',') {
//                indexIsPacket.add(false)
//                val digitString = inner.drop(i).takeWhile { it.isDigit() }
//                if (digitString.isNotBlank()) content.add(digitString.toInt())
//            }
//        }
//    }
//
//    override fun toString(): String {
//        return "Packet: ${if (packets.isNotEmpty()) "subPackets = $packets" else ""}" +
//                " ${if (content.isNotEmpty()) "content = $content" else ""}"
//    }
//}
//

fun solveB(input: List<String>): Long {
    TODO()
}


// if ints, left <= right
// if lists left[n] <= right[n] -> && left.length <= right.length
// if int and list, convertIntToList

// result = sum of indicies of correct signals