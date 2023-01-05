import kotlin.math.abs

class Day9(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    override val day get() = 9
    override val source get() = "$fileName"

    override fun part1() = Result(expected1, positionsVisited().distinct().count())

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

        operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = Pair(this.first + other.first, this.second + other.second)

        fun move(steps: Pair<Direction, Int>): List<Pair<Int, Int>> =
            (1..steps.second).map {
                tailAfter(headMove = steps.first)
            }

        private fun tailAfter(headMove: Day9.Direction): Pair<Int, Int> {
            head += headMove.step()
            tail += tailMoveAfter(headMove)
            return tail
        }

        private fun tailMoveAfter(headMove: Direction): Pair<Int, Int> = if (tailNeedsToMove())
            headMove.step() + aligningMoveFor(headMove) else Direction.NONE.step()

        private fun aligningMoveFor(headMove: Direction) =
            when (headMove) {
                Direction.R, Direction.L -> when {
                    tail.second < head.second -> Direction.U.step()
                    tail.second > head.second -> Direction.D.step()
                    else -> Direction.NONE.step()
                }
                Direction.U, Direction.D -> when {
                    tail.first < head.first -> Direction.R.step()
                    tail.first > head.first -> Direction.L.step()
                    else -> Direction.NONE.step()
                }
                else -> Direction.NONE.step()
            }


        private fun tailNeedsToMove(): Boolean =
            abs(head.first - tail.first) > 1 || abs(head.second - tail.second) > 1
    }

    private fun positionsVisited() = mutableListOf<Pair<Int, Int>>().let { visited ->
        moves.forEach { steps -> visited.addAll(rope.move(steps)) }
        visited
    }

    override fun part2() = PendingResult

    private fun parse(lines: List<String>): List<Pair<Direction, Int>> {

        fun parseMove(move: String): Pair<Direction, Int> {
            val (direction, steps) = move.split(" ")
            return Pair(Direction.valueOf(direction), steps.toInt())
        }

        return lines.map { parseMove(it) }
    }
}

fun main() {
    Solution.report(
        Day9("Day9-sample.txt", 13, 0),
        Day9("Day9.txt", 0, 0),
    )
}