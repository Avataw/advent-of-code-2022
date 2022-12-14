package day14

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay14 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(14)

        val solution = solveA(input)
        assertEquals(24, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(14)

        val solution = solveA(input)
        assertEquals(683, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(14)

        val solution = solveB(input)
        assertEquals(93, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(14)

        val solution = solveB(input)
        assertEquals(28821, solution)
    }
}
