package day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay03 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(3)

        val solution = solveA(input)
        assertEquals(157, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(3)

        val solution = solveA(input)
        assertEquals(8153, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(3)

        val solution = solveB(input)
        assertEquals(70, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(3)

        val solution = solveB(input)
        assertEquals(2342, solution)
    }
}
