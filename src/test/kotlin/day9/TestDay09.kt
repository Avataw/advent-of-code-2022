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
        assertEquals(13, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(9)

        val solution = solveA(input)
        assertEquals(5513, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(9)

        val solution = solveB(input)
        assertEquals(1, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(9)

        val solution = solveB(input)
        assertEquals(2427, solution)
    }
}
