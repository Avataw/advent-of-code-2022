package day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay15 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(15)

        val solution = solveA(input)
        assertEquals(1554315, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(15)

        val solution = solveA(input)
        assertEquals(1641531515, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(15)

        val solution = solveB(input)
        assertEquals(241533642, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(15)

        val solution = solveB(input)
        assertEquals(15155525, solution)
    }
}
