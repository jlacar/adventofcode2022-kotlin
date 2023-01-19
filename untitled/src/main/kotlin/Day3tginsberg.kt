/**
 * Trying to go through the thought process of getting the
 * kind of solution that tginsberg shared on his github repo
 */
class Day3tginsberg(val fileName: String) : AocSolution {
    override val description: String
        get() = "Day 3 - Rucksack Reorg, tginsberg style ($fileName)"

    val input = InputReader(fileName).lines

    override fun part1() = input.sumOf { it.sharedItem().priority() }

    override fun part2() = input.chunked(3).sumOf { it.sharedItem().priority() }

    private fun Char.priority(): Int = when (this) {
        in 'a'..'z' -> this - 'a' + 1
        in 'A'..'Z' -> this - 'A' + 27
        else -> 0
    }

    private fun String.halves() = listOf(
        substring(0, length / 2),
        substring(length / 2)
    )

    private fun String.sharedItem(): Char =
        this.halves().sharedItem()

    private fun List<String>.sharedItem(): Char =
        map { it.toSet() }
            .reduce { left, right -> left intersect right }
            .also { assert(it.size == 1) }  // run with -ea VM option to enable
            .first()
}

fun main() {
    Day3tginsberg("Day3-sample.txt") solution {
        part1() shouldBe 157
        part2() shouldBe 70
    }
    Day3tginsberg("Day3.txt") solution {
        part1() shouldBe 7446
        part2() shouldBe 2646
    }
    Day3tginsberg("Day3-alt.txt") solution {
        part1() shouldBe 8349
        part2() shouldBe 2681
    }
}