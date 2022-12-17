package day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay17 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(17)

        val solution = solveA(input)
        assertEquals(3068, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(17)

        val solution = solveA(input)
        assertEquals(3059, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(17)

        val solution = solveB(input)
        assertEquals(1514285714288, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(17)

        val solution = solveB(input)
        assertEquals(1500874635587, solution)
    }
}
