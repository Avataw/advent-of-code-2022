package day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay11 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(11)

        val solution = solveA(input)
        assertEquals(1154311, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(11)

        val solution = solveA(input)
        assertEquals(1641131111, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(11)

        val solution = solveB(input)
        assertEquals(241133642, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(11)

        val solution = solveB(input)
        assertEquals(11115525, solution)
    }
}
