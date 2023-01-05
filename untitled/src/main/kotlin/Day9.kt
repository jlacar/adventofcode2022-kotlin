import Day9.Direction.*
import kotlin.math.abs

class Day9(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    override val day get() = 9
    override val source get() = "$fileName"

    override fun part1() = Result(expected1, distinctPositionsVisited().count())

    enum class Direction {
        R { override fun step() = Pair(1, 0) },
        L { override fun step() = Pair(-1, 0) },
        U { override fun step() = Pair(0, 1) },
        D { override fun step() = Pair(0, -1) },
        NONE { override fun step() = Pair(0, 0) };

        abstract fun step(): Pair<Int, Int>
    }

    private val moves = parse(InputReader(fileName).lines())

    private val rope = object {
        var head = Pair(0, 0)
        var tail = Pair(0, 0)

        operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) =
            Pair(this.first + other.first, this.second + other.second)

        fun tailPositions(steps: Pair<Direction, Int>): List<Pair<Int, Int>> = steps.let { (headMove, count) ->
            (1..count).map { _ ->
                head += headMove.step()
                tail += tailMoveAfter(headMove)
                tail
            }
        }

        private fun tailMoveAfter(headMove: Direction): Pair<Int, Int> = if (tailShouldMove())
            headMove.step() + alignDiagonalTail(headMove) else NONE.step()

        private fun tailShouldMove(): Boolean =
            abs(head.first - tail.first) > 1 || abs(head.second - tail.second) > 1

        private fun alignDiagonalTail(headMove: Direction) =
            when (headMove) {
                R, L -> alignHorizontally()
                U, D -> alignVertically()
                else -> NONE.step()
            }

        private fun alignVertically() = when {
            tail.first < head.first -> R.step()
            tail.first > head.first -> L.step()
            else -> NONE.step()
        }

        private fun alignHorizontally() = when {
            tail.second < head.second -> U.step()
            tail.second > head.second -> D.step()
            else -> NONE.step()
        }
    }

    private fun distinctPositionsVisited() = moves.map { steps -> rope.tailPositions(steps) }.flatten().distinct()

    override fun part2() = PendingResult

    private fun parse(lines: List<String>): List<Pair<Direction, Int>> {

        fun parseMove(move: String): Pair<Direction, Int> {
            val (direction, steps) = move.split(" ")
            return Pair(valueOf(direction), steps.toInt())
        }

        return lines.map { parseMove(it) }
    }
}

fun main() {
    Solution.report(
        Day9("Day9-sample.txt", 13, 0),
        Day9("Day9.txt", 6563, 0),
    )
}