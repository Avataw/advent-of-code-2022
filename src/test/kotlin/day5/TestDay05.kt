package day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay05 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(5)

        val solution = solveA(input)
        assertEquals("CMZ", solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(5)

        val solution = solveA(input)
        assertEquals("QNHWJVJZW", solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(5)

        val solution = solveB(input)
        assertEquals("MCD", solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(5)

        val solution = solveB(input)
        assertEquals("BPCZJLFJW", solution)
    }
}
