package day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay01 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(1)

        val solution = solveA(input)
        assertEquals(24000, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(1)

        val solution = solveA(input)
        assertEquals(68775, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(1)

        val solution = solveB(input)
        assertEquals(45000, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(1)

        val solution = solveB(input)
        assertEquals(202585, solution)
    }
}
