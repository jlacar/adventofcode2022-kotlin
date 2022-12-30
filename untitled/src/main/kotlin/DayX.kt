class DayX(
    private val fileName: String,
    private val expected1: Any,
    private val expected2: Any) : Solution {
    override val day get() = 0
    override val source get() = "$fileName"


    private val input = InputReader(fileName).lines()
    override fun part1() = PendingResult
    override fun part2() = PendingResult
}
