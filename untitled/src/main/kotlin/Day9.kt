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
        D { override fun step() = Pair(0, -1) };

        abstract fun step(): Pair<Int, Int>
    }

    private val moves = parse(InputReader(fileName).lines())

    private val rope = object {
        var head = Pair(0, 0)
        var tail = Pair(0, 0)

        fun move(steps: Pair<Direction, Int>): List<Pair<Int, Int>> =
            (1..steps.second).map {
                whereIsTailAfter(movingIn = steps.first)
            }

        private fun whereIsTailAfter(movingIn: Day9.Direction): Pair<Int, Int> = Pair(0, 0)
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
        Day9("Day9-sample.txt", 0, 0),
    )
}