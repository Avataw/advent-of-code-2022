package day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay02 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(2)

        val solution = solveA(input)
        assertEquals(15, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(2)

        val solution = solveA(input)
        assertEquals(11386, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(2)

        val solution = solveB(input)
        assertEquals(12, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(2)

        val solution = solveB(input)
        assertEquals(13600, solution)
    }
}
