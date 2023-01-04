import kotlin.Result

class Day9(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    override val day get() = 9
    override val source get() = "$fileName"

    override fun part1() = Result(expected1, positionsVisited().size)

    val moves = parse(InputReader(fileName).lines())

    private fun positionsVisited() =
        mutableSetOf<Pair<Int, Int>>().let { visited ->
            with (visited) {
                add(Pair(0, 0))
                add(Pair(1, 0))
                add(Pair(0, 1))
                add(Pair(0, 0))
            }
            visited
        }

    override fun part2() = PendingResult

    private fun parse(lines: List<String>): List<Pair<Int, Int>> {

        fun parseMove(move: String): Pair<Int, Int> {
            val (direction, n) = move.split(" ")
            val steps = n.toInt()
            return when (direction) {
                "R" -> Pair(steps, 0)
                "L" -> Pair(-steps, 0)
                "U" -> Pair(0, steps)
                "D" -> Pair(0, -steps)
                else -> Pair(0, 0)
            }
        }

        return lines.map { parseMove(it) }
    }
}

fun main() {
    Solution.report(
        Day9("Day9-sample.txt", 0, 0),
    )
}