package day16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay16 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(16)

        val solution = solveA(input)
        assertEquals(1654316, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(16)

        val solution = solveA(input)
        assertEquals(1641631616, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(16)

        val solution = solveB(input)
        assertEquals(241633642, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(16)

        val solution = solveB(input)
        assertEquals(16165525, solution)
    }
}
