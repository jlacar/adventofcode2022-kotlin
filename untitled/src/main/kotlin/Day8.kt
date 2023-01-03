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

    override fun part2() = Result(expected2, scenicScores().max())

    private fun scenicScores() =
        (1 until forestRows.lastIndex).map { row ->
            (1 until forestCols.lastIndex).map { col ->
                scenicScores(forestRows[row], col) * scenicScores(forestCols[col], row)
            }
        }.flatten()

    private fun scenicScores(trees: CharArray, pos: Int) = frontScore(trees, pos) * backScore(trees, pos)

    private fun frontScore(trees: CharArray, pos: Int) =
        scenicScore(trees[pos], fromFront(trees, pos).reversed())

    private fun backScore(trees: CharArray, pos: Int) =
        scenicScore(trees[pos], fromBehind(trees, pos))

    private fun scenicScore(tree: Char, otherTrees: List<Char>) =
        if (otherTrees.isEmpty()) 0
        else otherTrees.dropWhile { it < tree }.size.let { obscured ->
            if (obscured == 0) otherTrees.size else otherTrees.size - obscured + 1
        }

    private fun visibleTrees() = perimeter() + visibleInterior()

    private fun perimeter() = 2 * forestRows.size + 2 * forestRows[0].size - 4

    private fun visibleInterior() =
        (1 until forestRows.lastIndex).sumOf { row ->
            (1 until forestCols.lastIndex).count { col -> canSeeTreeAt(row, col) }
        }

    private fun canSeeTreeAt(row: Int, col: Int) = canBeSeen(forestRows[row], col) || canBeSeen(forestCols[col], row)

    private fun canBeSeen(trees: CharArray, pos: Int) = trees[pos].let {
        isNotObscured(fromFront(trees, pos), it) || isNotObscured(fromBehind(trees, pos), it)
    }

    private fun isNotObscured(trees: List<Char>, tree: Char) = trees.count { it >= tree } == 0

    private fun fromFront(trees: CharArray, pos: Int) = trees.dropLast(trees.size - pos)

    private fun fromBehind(trees: CharArray, pos: Int) = trees.drop(pos + 1)
}

fun main() {
    listOf(
        Day8("Day8-sample.txt", 21, 8),
        Day8("Day8.txt", 1690, 535680),
        Day8("Day8-alt.txt", 1698, 672280),
    ).forEach { it.report() }
}