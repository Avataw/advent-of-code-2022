package day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay13 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(13)

        val solution = solveA(input)
        assertEquals(1354313, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(13)

        val solution = solveA(input)
        assertEquals(1641331313, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(13)

        val solution = solveB(input)
        assertEquals(241333642, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(13)

        val solution = solveB(input)
        assertEquals(13135525, solution)
    }
}
