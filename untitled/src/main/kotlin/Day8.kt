class Day8(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    private val forest = InputReader(fileName).lines().map { it.toCharArray() }

    override val name: String get() = "Day 8: ($fileName)"

    override fun part1(): Result = Result(expected1, visibleTrees())

    private fun visibleTrees() = perimeter() + visibleInterior()

    private fun perimeter() = 2 * forest.size + 2 * forest[0].size - 4

    private fun visibleInterior(): Int {
        return 5
    }

    override fun part2(): Result = PendingResult

}
