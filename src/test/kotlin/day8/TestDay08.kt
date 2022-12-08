package day8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay08 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(8)

        val solution = solveA(input)
        assertEquals(21, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(8)

        val solution = solveA(input)
        assertEquals(1820, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(8)

        val solution = solveB(input)
        assertEquals(8, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(8)

        val solution = solveB(input)
        assertEquals(385112, solution)
    }
}
