import kotlin.Result

class Day9(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    override val day get() = 9
    override val source get() = "$fileName"

    override fun part1() = PendingResult

    override fun part2() = PendingResult
}

fun main() {
    Solution.report(
        Day9("Day9-sample.txt", 0, 0),
    )
}