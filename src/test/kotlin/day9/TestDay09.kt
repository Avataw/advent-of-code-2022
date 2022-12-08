package day9

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay09 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(9)

        val solution = solveA(input)
        assertEquals(95439, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(9)

        val solution = solveA(input)
        assertEquals(1649399, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(9)

        val solution = solveB(input)
        assertEquals(24933642, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(9)

        val solution = solveB(input)
        assertEquals(1915525, solution)
    }
}