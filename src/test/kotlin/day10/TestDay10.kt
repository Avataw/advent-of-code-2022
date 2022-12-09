package day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay10 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(10)

        val solution = solveA(input)
        assertEquals(1054310, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(10)

        val solution = solveA(input)
        assertEquals(1641031010, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(10)

        val solution = solveB(input)
        assertEquals(241033642, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(10)

        val solution = solveB(input)
        assertEquals(11015525, solution)
    }
}
