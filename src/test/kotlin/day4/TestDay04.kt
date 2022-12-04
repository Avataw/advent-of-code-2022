package day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay04 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(4)

        val solution = solveA(input)
        assertEquals(2, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(4)

        val solution = solveA(input)
        assertEquals(560, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(4)

        val solution = solveB(input)
        assertEquals(4, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(4)

        val solution = solveB(input)
        assertEquals(839, solution)
    }
}
