package day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay12 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(12)

        val solution = solveA(input)
        assertEquals(31, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(12)

        val solution = solveA(input)
        assertEquals(383, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(12)

        val solution = solveB(input)
        assertEquals(29, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(12)

        val solution = solveB(input)
        assertEquals(377, solution)
    }
}
