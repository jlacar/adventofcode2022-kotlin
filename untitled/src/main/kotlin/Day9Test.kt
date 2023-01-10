import Direction.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

import kotlin.math.abs

class Day9Test {

    val origin = Position(0, 0)

    @Nested
    inner class `Position functions` {
        @Nested
        inner class `step(Direction)` {
            @Test
            fun `R increases X by 1 `() =
                assertEquals(Position(1, 0), Position(0, 0).step(R))

            @Test
            fun `L decreases X by 1 `() =
                assertEquals(Position(0, 0), Position(1, 0).step(L))

            @Test
            fun `U increases Y by 1 `() =
                assertEquals(Position(0, 1), Position(0, 0).step(U))

            @Test
            fun `D decreases Y by 1 `() =
                assertEquals(Position(0, 0), Position(0, 1).step(D))
        }

        @Nested
        inner class `distanceTo(Position)` {

            @ParameterizedTest(name = "{0} step from origin, expect ({1}, {2})")
            @CsvSource(
                delimiter = ',', textBlock =
                """ R, -1, 0
                L, 1, 0
                U, 0, -1
                D, 0, 1
                NONE, 0, 0"""
            )
            fun `to origin from relative position `(dir: Direction, xE: Int, yE: Int) {
                assertEquals(Position(xE, yE), origin.step(dir).distanceTo(origin))
            }
        }

        @Nested
        inner class `stepToward(Position)` {
            @ParameterizedTest(name = "from ({0}, {1}) to ({2}, {3})")
            @CsvSource(
                delimiter = ',', textBlock =
                """ 2,  0, 1,  0
                -2, 0, -1, 0
                0, 2, 0, 1
                0, -2, 0, -1"""
            )
            fun `lateral step toward origin `(xS: Int, yS: Int, xE: Int, yE: Int) {
                assertEquals(Position(xE, yE), Position(xS, yS).stepToward(origin))
            }

            @ParameterizedTest(name = "from ({0}, {1}) to ({2}, {3})")
            @CsvSource(
                delimiter = ',', textBlock =
                """ 2,  1, 1,  0
                -2, 1, -1, 0
                2, -1, 1, 0
                -2, -1, -1, 0
                1, 2, 0, 1
                -1, 2, 0, 1
                1, -2, 0, -1
                -1, -2, 0, -1"""
            )
            fun `diagonal step toward origin `(xS: Int, yS: Int, xE: Int, yE: Int) {
                assertEquals(Position(xE, yE), Position(xS, yS).stepToward(origin))
            }
        }

        @Nested
        inner class `touches(Position)` {

            @ParameterizedTest(name = "({0}, {1}) touches origin")
            @CsvSource(
                delimiter = ',', textBlock =
                """ 0, 0
                0, 1
                0, -1
                1, 0
                1, 1
                1, -1
                -1, 0
                -1, 1
                -1, -1"""
            )
            fun `touches origin`(x: Int, y: Int) =
                assertTrue(Position(x, y).touches(origin))

            @ParameterizedTest(name = "({0}, {1}) does not touch origin")
            @CsvSource(
                delimiter = ',', textBlock =
                """ 0, 2
                0, -2
                2, 0
                -2, 0
                2, 1
                2, -1
                -2, 1
                -2, -1"""
            )
            fun `does not touch origin`(x: Int, y: Int) =
                assertFalse(Position(x, y).touches(origin))
        }
    }

    @Nested
    inner class `Knot functions` {
        private val tail = Knot()
        private val head = Knot(tail)

        @ParameterizedTest(name = "move({0}, {1}) -> ({2}, {3})")
        @CsvSource(
            delimiter = ',', textBlock =
            """R, 3, 3, 0
           U, 3, 0, 3
           L, 4, -4, 0
           D, 4, 0, -4"""
        )
        fun `move n steps`(dir: Direction, steps: Int, xE: Int, yE: Int) {
            head.move(dir, steps)
            assertEquals(Position(xE, yE), head.currentPosition)
        }

        @ParameterizedTest(name = "move({0}) -> ({1}, {2})")
        @CsvSource(
            delimiter = ',', textBlock =
            """R, 1, 0
           U, 0, 1
           L, -1, 0
           D, 0, -1"""
        )
        fun `move defaults to 1 step`(dir: Direction, xE: Int, yE: Int) {
            head.move(dir)
            assertEquals(Position(xE, yE), head.currentPosition)
        }

        @ParameterizedTest(name = "head moves {0}, tail ends at ({1}, {2})")
        @CsvSource(
            delimiter = ',', textBlock =
            """RRRRUU, 4, 1
           RRR, 2, 0
           R, 0, 0
           RRRUUDDLL, 2, 0
           RRRUUDDL, 3, 1"""
        )
        fun `tail maintains contact with head`(dirs: String, xE: Int, yE: Int) {
            dirs.toCharArray().forEach { dir ->
                head.move(Direction.valueOf(dir.toString()))
            }
            assertEquals(Position(xE, yE), tail.currentPosition)
        }
    }

    @Nested
    inner class `Rope functions` {
        @Test
        fun `two-knot rope`() {
            val rope = Rope()
            rope.move(
                """
                R 4
                U 4
                L 3
                D 1
                R 4
                D 1
                L 5
                R 2""".trimIndent().lines()
            )
            assertEquals(13, rope.tail.visited.distinct().count())
        }

        @Test
        fun `ten-knot rope`() {
            val rope = Rope(10)
            rope.move(
                """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20""".trimIndent().lines()
            )
            assertEquals(36, rope.tail.visited.distinct().count())
        }
    }
}

enum class Direction { R, L, U, D, NONE }

data class Position(val x: Int, val y: Int) {

    operator fun plus(other: Position) = Position(this.x + other.x, this.y + other.y)

    fun step(dir: Direction) = when (dir) {
        R -> this.copy(x = this.x + 1)
        L -> this.copy(x = this.x - 1)
        U -> this.copy(y = this.y + 1)
        D -> this.copy(y = this.y - 1)
        else -> this.copy()
    }

    fun distanceTo(other: Position) = Position(other.x - this.x, other.y - this.y)

    fun stepToward(other: Position) = distanceTo(other).let { (dx, dy) ->
        this.step(direction(dx, R, L)).step(direction(dy, U, D))
    }

    fun touches(other: Position) = distanceTo(other).let { (dx, dy) -> abs(dx) <= 1 && abs(dy) <= 1 }
    private fun direction(delta: Int, plus: Direction, minus: Direction) = when {
        delta > 0 -> plus
        delta < 0 -> minus
        else -> NONE
    }

    override fun toString() = "($x, $y)"
}

class Knot(val tail: Knot? = null) {

    val visited = mutableListOf<Position>(Position(0, 0))
    val currentPosition
        get() = visited.last()

    fun move(dir: Direction, steps: Int = 1) = (1..steps).forEach {
        visited.add(currentPosition.step(dir))
        tail?.maintainContact(this)
    }

    private fun maintainContact(other: Knot) {
        if (this.currentPosition.touches(other.currentPosition)) return
        visited.add(currentPosition.stepToward(other.currentPosition))
        tail?.maintainContact(this)
    }
}

class Rope(knots: Int = 2) {
    val tail = Knot()
    private val head = (2..knots).fold(tail) { tail, _ -> Knot(tail) }

    fun move(steps: List<String>) = steps.forEach {
        it.split(" ").let { (dir, steps) ->
            head.move(valueOf(dir), steps.toInt())
        }
    }
}

class Day9alt(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int
) : Solution {
    override val day get() = 9
    override val source get() = "$fileName"

    val moves = InputReader(fileName).lines()

    override fun part1() = Result(expected1, distinctPositionsVisitedByTail(Rope()))

    override fun part2() = Result(expected2, distinctPositionsVisitedByTail(Rope(10)))

    private fun distinctPositionsVisitedByTail(rope: Rope): Int {
        rope.move(moves)
        return rope.tail.visited.distinct().count()
    }
}

fun main() {
    Solution.report(
        Day9alt("Day9-sample.txt", 13, 1),
        Day9alt("Day9.txt", 6563, 2653),
    )
}