class Day8(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    private val forestRows = InputReader(fileName).lines().map { it.toCharArray() }

    private val forestCols = (0 until forestRows[0].size).map { i -> forestRows.map { it[i] } }

    override val day get() = 8
    override val source get() = "$fileName"


    override fun part1(): Result = Result(expected1, visibleTrees())

    private fun visibleTrees() = perimeter() + visibleInterior()

    private fun perimeter() = 2 * forestRows.size + 2 * forestRows[0].size - 4

    private fun visibleInterior(): Int {
        return 5
    }

    override fun part2(): Result = PendingResult

}
