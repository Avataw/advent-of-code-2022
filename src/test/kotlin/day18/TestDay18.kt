package day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay18 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(18)

        val solution = solveA(input)
        assertEquals(64, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(18)

        val solution = solveA(input)
        assertEquals(4548, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(18)

        val solution = solveB(input)
        assertEquals(58, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(18)

        val solution = solveB(input)
        assertEquals(2588, solution)
    }
}
