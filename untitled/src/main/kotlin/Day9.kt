import Day9.Direction.*
import kotlin.math.abs

class Day9(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    override val day get() = 9
    override val source get() = "$fileName"

    override fun part1() = Result(expected1, distinctTailPositions().count())

    data class Position(val x: Int, val y: Int) {
        operator fun plus(other: Position) = Position(this.x + other.x, this.y + other.y)

        fun move(dir: Direction) = this + dir.step()
    }

    enum class Direction {
        R { override fun step() = Position(1, 0) },
        L { override fun step() = Position(-1, 0) },
        U { override fun step() = Position(0, 1) },
        D { override fun step() = Position(0, -1) },
        NONE { override fun step() = Position(0, 0) };

        abstract fun step(): Position
    }

    private val moves = parse(InputReader(fileName).lines())

    private val rope = object {
        var head = Position(0, 0)
        var tail = Position(0, 0)

        fun tailPositions(steps: Pair<Direction, Int>) = steps.let { (direction, count) ->
            (1..count).map { _ ->
                head = head.move(direction)
                tail = follow(direction)
                tail
            }
        }

        private fun follow(direction: Direction) = if (tailShouldMove())
            tail.move(direction) + alignDiagonalTail(direction) else tail

        private fun tailShouldMove() = abs(head.x - tail.x) > 1 || abs(head.y - tail.y) > 1

        private fun alignDiagonalTail(direction: Direction) =
            when (direction) {
                R, L -> alignTailHorizontally()
                U, D -> alignTailVertically()
                else -> NONE.step()
            }

        private fun alignTailVertically() = when {
            tail.x < head.x -> R.step()
            tail.x > head.x -> L.step()
            else -> NONE.step()
        }

        private fun alignTailHorizontally() = when {
            tail.y < head.y -> U.step()
            tail.y > head.y -> D.step()
            else -> NONE.step()
        }
    }

    private fun distinctTailPositions() = moves.map { steps -> rope.tailPositions(steps) }.flatten().distinct()

    override fun part2() = PendingResult

    private fun parse(lines: List<String>) = lines.map { move ->
        move.split(" ").let { (direction, steps) ->
            Pair(valueOf(direction), steps.toInt())
        }
    }
}

fun main() {
    Solution.report(
        Day9("Day9-sample.txt", 13, 0),
        Day9("Day9.txt", 6563, 0),
    )
}