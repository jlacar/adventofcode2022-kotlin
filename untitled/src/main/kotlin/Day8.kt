class Day8(private val fileName: String) : AocSolution {
    override val description: String get() = "Day 8 - Treetop Tree House ($fileName)"

    override fun part1() = visibleTrees()

    override fun part2() = scenicScores().max()

    private val forestRows = InputReader(fileName).lines.map { it.toCharArray() }
    private val forestCols = forestRows[0].indices.map { i -> forestRows.map { it[i] }.toCharArray() }

    private fun visibleTrees() = perimeter() + visibleFromOutside()

    private fun perimeter() = 2 * forestRows.size + 2 * forestRows[0].size - 4

    private fun visibleFromOutside() =
        (1 until forestRows.lastIndex).sumOf { row ->
            (1 until forestCols.lastIndex).count { col -> canSeeTreeAt(row, col) }
        }

    private fun canSeeTreeAt(row: Int, col: Int) =
        canSeeFromOutside(forestRows[row], col) || canSeeFromOutside(forestCols[col], row)

    private fun canSeeFromOutside(trees: CharArray, pos: Int) = trees[pos].let {
        isNotObscured(it, front(trees, pos)) || isNotObscured(it, back(trees, pos))
    }

    private fun isNotObscured(tree: Char, trees: List<Char>) = trees.none { it >= tree }

    private fun front(trees: CharArray, pos: Int) = trees.dropLast(trees.size - pos)

    private fun back(trees: CharArray, pos: Int) = trees.drop(pos + 1)

    private fun scenicScores() =
        (1 until forestRows.lastIndex).map { row ->
            (1 until forestCols.lastIndex).map { col ->
                scenicScore(forestRows[row], col) * scenicScore(forestCols[col], row)
            }
        }.flatten()

    private fun scenicScore(trees: CharArray, pos: Int) =
        scenicScore(trees[pos], front(trees, pos).reversed()) *
        scenicScore(trees[pos], back(trees, pos))

    private fun scenicScore(tree: Char, otherTrees: List<Char>) =
        if (otherTrees.isNotEmpty()) howManyCanBeSeen(otherTrees, tree) else 0

    private fun howManyCanBeSeen(otherTrees: List<Char>, tree: Char) =
        otherTrees.dropWhile { it < tree }.let { remaining ->
            otherTrees.size - remaining.size + firstTree(remaining)
        }

    private fun firstTree(remaining: List<Char>) = if (remaining.isEmpty()) 0 else 1
}

fun main() {
    Day8("Day8-sample.txt") solution {
        part1() shouldBe 21
        part2() shouldBe 8
    }
    Day8("Day8.txt") solution {
        part1() shouldBe 1690
        part2() shouldBe 535680
    }
    Day8("Day8-alt.txt") solution {
        part1() shouldBe 1698
        part2() shouldBe 672280
    }
}