package day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay13 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(13)

        val solution = solveA(input)
        assertEquals(13, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(13)

        val solution = solveA(input)
        assertEquals(5198, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(13)

        val solution = solveB(input)
        assertEquals(140, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(13)

        val solution = solveB(input)
        assertEquals(22344, solution)
    }
}
