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
            @ParameterizedTest(name = "origin.step({0}) -> ({1}, {2})")
            @CsvSource(delimiter = ',', textBlock =
                """R,    1, 0
                   L,   -1, 0
                   U,    0, 1
                   D,    0,-1
                   NONE, 0, 0"""
            )
            fun `from origin, step`(direction: Direction, xE: Int, yE: Int) =
                assertEquals(Position(xE, yE), origin.step(direction))

        }

        @Nested
        inner class `distanceTo(Position)` {

            @ParameterizedTest(name = "{0} step from origin, expect ({1}, {2})")
            @CsvSource(
                delimiter = ',', textBlock =
                """R,   -1, 0
                   L,    1, 0
                   U,    0,-1
                   D,    0, 1
                   NONE, 0, 0"""
            )
            fun `to origin from relative position `(dir: Direction, xE: Int, yE: Int) {
                assertEquals(Position(xE, yE), origin.step(dir).distanceTo(origin))
            }

            @ParameterizedTest(name = "({0}, {1}).distanceTo(origin) should be ({2}, {3})")
            @CsvSource(
                delimiter = ',', textBlock =
                """3, 0  , -3, 0
                  -3, 0  ,  3, 0
                   0, 3  ,  0,-3
                   0,-3  ,  0, 3
                   4, 5  , -4,-5
                  -5,-4  ,  5, 4"""
            )
            fun `distanceTo relative to origin `(xS: Int, yS: Int, xE: Int, yE: Int) =
                assertEquals(Position(xE, yE), Position(xS, yS).distanceTo(origin))
        }

        @Nested
        inner class `moveToward(Position)` {
            @ParameterizedTest(name = "from ({0}, {1}) to ({2}, {3})")
            @CsvSource(
                delimiter = ',', textBlock =
                """2, 0  ,  1, 0
                  -2, 0  , -1, 0
                   0, 2  ,  0, 1
                   0,-2  ,  0,-1"""
            )
            fun `lateral move toward origin `(xS: Int, yS: Int, xE: Int, yE: Int) {
                assertEquals(Position(xE, yE), Position(xS, yS).moveToward(origin))
            }

            @ParameterizedTest(name = "from ({0}, {1}) to ({2}, {3})")
            @CsvSource(
                delimiter = ',', textBlock =
                """2, 1  ,  1, 0
                  -2, 1  , -1, 0
                   2,-1  ,  1, 0
                  -2,-1  , -1, 0
                   1, 2  ,  0, 1
                  -1, 2  ,  0, 1
                   1,-2  ,  0,-1
                  -1,-2  ,  0,-1"""
            )
            fun `diagonal move toward origin `(xS: Int, yS: Int, xE: Int, yE: Int) {
                assertEquals(Position(xE, yE), Position(xS, yS).moveToward(origin))
            }
        }

        @Nested
        inner class `touches(Position)` {

            @ParameterizedTest(name = "({0}, {1}) touches origin")
            @CsvSource(
                delimiter = ',', textBlock =
                """ 0, 0
                    0, 1
                    0,-1
                    1, 0
                    1, 1
                    1,-1
                   -1, 0
                   -1, 1
                   -1,-1"""
            )
            fun `touches origin`(x: Int, y: Int) =
                assertTrue(Position(x, y).touches(origin))

            @ParameterizedTest(name = "({0}, {1}) does not touch origin")
            @CsvSource(
                delimiter = ',', textBlock =
                """ 0, 2
                    0,-2
                    2, 0
                   -2, 0
                    2, 1
                    2,-1
                   -2, 1
                   -2,-1"""
            )
            fun `does not touch origin`(x: Int, y: Int) =
                assertFalse(Position(x, y).touches(origin))
        }
    }

    @Nested
    inner class `Knot functions` {
        private val tail = Knot()
        private val head = Knot(tail)

        @Test
        fun `new Knot starts at origin`() =
            assertEquals(origin, Knot().currentPosition)

        @ParameterizedTest(name = "move({0}, {1}) -> ({2}, {3})")
        @CsvSource(
            delimiter = ',', textBlock =
            """R,3  ,  3, 0
               U,3  ,  0, 3
               L,4  , -4, 0
               D,4  ,  0,-4"""
        )
        fun `move n steps`(dir: Direction, steps: Int, xE: Int, yE: Int) {
            head.move(dir, steps)
            assertEquals(Position(xE, yE), head.currentPosition)
        }

        @ParameterizedTest(name = "move({0}) -> ({1}, {2})")
        @CsvSource(
            delimiter = ',', textBlock =
            """R  ,  1, 0
               U  ,  0, 1
               L  , -1, 0
               D  ,  0,-1"""
        )
        fun `move defaults to 1 step`(dir: Direction, xE: Int, yE: Int) {
            head.move(dir)
            assertEquals(Position(xE, yE), head.currentPosition)
        }

        @ParameterizedTest(name = "head moves {0}, tail ends at ({1}, {2})")
        @CsvSource(
            delimiter = ',', textBlock =
            """R        , 0, 0
               L        , 0, 0
               U        , 0, 0
               D        , 0, 0
               RR       , 1, 0
               RRR      , 2, 0
               LL       ,-1, 0
               LLL      ,-2, 0
               UU       , 0, 1
               UUU      , 0, 2
               DD       , 0,-1
               DDD      , 0,-2
               RUU      , 1, 1
               RDD      , 1,-1
               LUU      ,-1, 1
               LDD      ,-1,-1
               URR      , 1, 1
               DRR      , 1,-1
               ULL      ,-1, 1
               DLL      ,-1,-1
               LDRURUL  , 0, 0
               RRRRUU   , 4, 1
               RRRUUDDLL, 2, 0
               RRRUUDDL , 3, 1"""
        )
        fun `tail keeps up with head`(dirs: String, xE: Int, yE: Int) {
            dirs.toCharArray().forEach { head.move(valueOf(it.toString())) }
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
    operator fun minus(other: Position) = Position(this.x - other.x, this.y - other.y)

    fun step(dir: Direction) = when (dir) {
        R -> this.copy(x = this.x + 1)
        L -> this.copy(x = this.x - 1)
        U -> this.copy(y = this.y + 1)
        D -> this.copy(y = this.y - 1)
        else -> this.copy()
    }

    fun distanceTo(other: Position) = other - this

    fun touches(other: Position) = distanceTo(other).let { (dx, dy) ->
        abs(dx) <= 1 && abs(dy) <= 1
    }

    fun moveToward(other: Position) = distanceTo(other).let { (dx, dy) ->
        this.step(direction(dx, R, L)).step(direction(dy, U, D))
    }

    private fun direction(delta: Int, plus: Direction, minus: Direction) = when {
        delta > 0 -> plus
        delta < 0 -> minus
        else -> NONE
    }

    override fun toString() = "($x, $y)"
}

class Knot(val tail: Knot? = null) {

    val visited = mutableListOf(Position(0, 0))
    val currentPosition get() = visited.last()

    fun move(dir: Direction, steps: Int = 1) = (1..steps).forEach { _ ->
        visited.add(currentPosition.step(dir))
        tail?.keepUpWith(this)
    }

    private fun keepUpWith(other: Knot) {
        if (currentPosition.touches(other.currentPosition)) return
        visited.add(currentPosition.moveToward(other.currentPosition))
        tail?.keepUpWith(this)
    }
}

class Rope(knots: Int = 2) {
    val tail = Knot()
    private val head = (2..knots).fold(tail) { tail, _ -> Knot(tail) }

    fun move(moves: List<String>): List<Position> {
        moves.forEach {it.split(" ").let { (direction, steps) ->
            head.move(valueOf(direction), steps.toInt())
        }}
        return head.visited.toList()
    }
}

class Day9alt(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int
) : Solution {
    override val day get() = 9
    override val source get() = "$fileName"

    private val allMoves = InputReader(fileName).lines()

    override fun part1() = Result(expected1, distinctPositionsVisitedByTail(Rope()))

    override fun part2() = Result(expected2, distinctPositionsVisitedByTail(Rope(10)))

    private fun distinctPositionsVisitedByTail(rope: Rope): Int {
        rope.move(allMoves)
        return rope.tail.visited.distinct().count()
    }
}

fun main() {
    Solution.report(
        Day9alt("Day9-sample.txt", 13, 1),
        Day9alt("Day9-sample2.txt", 88, 36),
        Day9alt("Day9.txt", 6563, 2653),
    )
}