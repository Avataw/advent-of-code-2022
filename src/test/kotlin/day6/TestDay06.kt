package day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay06 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(6)

        val solution = solveA(input)
        assertEquals(listOf(7, 5, 6, 10, 11), solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(6)

        val solution = solveA(input)
        assertEquals(listOf(1794), solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(6)

        val solution = solveB(input)
        assertEquals(listOf(19, 23, 23, 29, 26), solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(6)

        val solution = solveB(input)
        assertEquals(listOf(2851), solution)
    }
}
