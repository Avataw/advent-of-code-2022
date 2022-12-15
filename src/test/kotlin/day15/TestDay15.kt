package day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput
import readTestInput

class TestDay15 {
    @Test
    fun `should work for the given test input`() {
        val input = readTestInput(15)

        val solution = solveA(input, 10)
        assertEquals(26, solution)
    }

    @Test
    fun `should work for the actual input`() {
        val input = readInput(15)

        val solution = solveA(input, 2000000)
        assertEquals(5832528, solution)
    }

    @Test
    fun `should work with b for the given test input`() {
        val input = readTestInput(15)

        val solution = solveB(input, 20)
        assertEquals(56000011, solution)
    }

    @Test
    fun `should work with b for the actual input`() {
        val input = readInput(15)

        val solution = solveB(input, 4000000)
        assertEquals(13360899249595, solution)
    }
}
