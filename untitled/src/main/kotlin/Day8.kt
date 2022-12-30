class Day8(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    private val forestRows = InputReader(fileName).lines().map { it.toCharArray() }

    private val forestCols = (0 until forestRows[0].size).map { i -> forestRows.map { it[i] }.toCharArray() }

    override val day get() = 8
    override val source get() = "$fileName"

    override fun part1(): Result = Result(expected1, visibleTrees())

    private fun visibleTrees() = perimeter() + visibleInterior()

    private fun perimeter() = 2 * forestRows.size + 2 * forestRows[0].size - 4

    private fun isVisible(trees: CharArray, position: Int) = trees[position].let {
        isVisible(fromFront(trees, position), it) || isVisible(fromBehind(trees, position), it)
    }

    private fun isVisible(trees: List<Char>, tree: Char) = trees.count { it >= tree } == 0

    private fun fromFront(chars: CharArray, col: Int) = chars.slice(0 until col)

    private fun fromBehind(chars: CharArray, col: Int) = chars.slice(col + 1 until chars.size)

    private fun isVisible(row: Int, col: Int) =
        isVisible(forestRows[row], col) || isVisible(forestCols[col], row)

    private fun visibleInterior(): Int {
        val visible = mutableListOf<Char>()
        (1 until forestRows.lastIndex).forEach { row ->
            (1 until forestCols.lastIndex).forEach { col ->
                if (isVisible(row, col)) visible.add(forestRows[row][col])
            }
        }
        return visible.count()
    }

    override fun part2(): Result = PendingResult

}