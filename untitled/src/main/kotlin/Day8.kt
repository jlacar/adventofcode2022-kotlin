class Day8(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    private val forestRows = InputReader(fileName).lines().map { it.toCharArray() }

    private val forestCols = (0 until forestRows[0].size).map { i -> forestRows.map { it[i] }.toCharArray() }

    override val day get() = 8
    override val source get() = "$fileName"

    override fun part1() = Result(expected1, visibleTrees())

    override fun part2() = PendingResult // Result(expected2, scenicScores().max())

    private fun scenicScores(): List<Int> {
        val scores = mutableListOf<Int>()
        forestRows.indices.forEach { row ->
            forestCols.indices.forEach { col ->
                scores.add(scenicScores(forestRows[row], col)).also { println("row $row ${scores.last()}") }
                scores.add(scenicScores(forestCols[col], row)).also { println("col $col ${scores.last()}") }
            }
        }
        return scores
    }

    private fun scenicScores(trees: CharArray, position: Int): Int {
        val frontScore = position - front(trees, position).dropLastWhile { it <= trees[position] }.size
        val backScore = trees.size - back(trees, position).dropWhile { it <= trees[position] }.size - position - 1
        println("trees ${trees.joinToString(",")} front $frontScore behind $backScore")
        return frontScore + backScore
    }

    private fun visibleTrees() = perimeter() + visibleInterior()

    private fun perimeter() = 2 * forestRows.size + 2 * forestRows[0].size - 4

    private fun visibleInterior(): Int {
        val visible = mutableListOf<Char>()
        (1 until forestRows.lastIndex).forEach { row ->
            (1 until forestCols.lastIndex).forEach { col ->
                if (isVisible(row, col)) visible.add(forestRows[row][col])
            }
        }
        return visible.count()
    }

    private fun isVisible(trees: CharArray, position: Int) = trees[position].let {
        isVisible(front(trees, position), it) || isVisible(back(trees, position), it)
    }

    private fun isVisible(trees: List<Char>, tree: Char) = trees.count { it >= tree } == 0

    private fun front(trees: CharArray, index: Int) = trees.dropLast(trees.size - index)

    private fun back(trees: CharArray, index: Int) = trees.drop(index + 1)

    private fun isVisible(row: Int, col: Int) =
        isVisible(forestRows[row], col) || isVisible(forestCols[col], row)

}

fun main() {
    println(Day8("Day8-test.txt", 0, 0).part2().report())
}