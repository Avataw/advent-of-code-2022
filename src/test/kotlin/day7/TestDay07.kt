package day7

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay07 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(7)

        val solution = solveA(input)
        assertEquals(95437, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(7)

        val solution = solveA(input)
        assertEquals(1648397, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(7)

        val solution = solveB(input)
        assertEquals(24933642, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(7)

        val solution = solveB(input)
        assertEquals(1815525, solution)
    }
}
