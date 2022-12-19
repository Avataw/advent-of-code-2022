package day19

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay19 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(19)

        val solution = solveA(input)
        assertEquals(33, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(19)

        val solution = solveA(input)
        assertEquals(1624, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(19)

        val solution = solveB(input)
        assertEquals(3472, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(19)

        val solution = solveB(input)
        assertEquals(12628, solution)
    }
}
