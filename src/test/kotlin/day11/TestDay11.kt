package day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay11 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(11)

        val solution = solveA(input)
        assertEquals(10605, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(11)

        val solution = solveA(input)
        assertEquals(120756, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(11)

        val solution = solveB(input)
        assertEquals(2713310158, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(11)

        val solution = solveB(input)
        assertEquals(39109444654, solution)
    }
}
