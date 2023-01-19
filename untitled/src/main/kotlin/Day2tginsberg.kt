/* Exercise to go through the thought process for coming up with solution shared by tginsberg */
class Day2tginsberg(private val fileName: String) : AocSolution {
    override val description: String get() = "Day 2 - RPS tginsberg approach ($fileName)"

    private val rounds = InputReader(fileName).lines

    override fun part1() = score(
        mapOf(
            "A X" to 1 + 3, // rock -> draw
            "B X" to 1 + 0, // rock -> lose
            "C X" to 1 + 6, // rock -> win
            "A Y" to 2 + 6, // paper -> win
            "B Y" to 2 + 3, // paper -> draw
            "C Y" to 2 + 0, // paper -> lose
            "A Z" to 3 + 0, // scissors -> lose
            "B Z" to 3 + 6, // scissors -> win
            "C Z" to 3 + 3, // scissors -> draw
        )
    )

    override fun part2() = score(
        mapOf(
            "A X" to 0 + 3, // lose with scissors
            "B X" to 0 + 1, // lose with rock
            "C X" to 0 + 2, // lose with paper
            "A Y" to 3 + 1, // draw with rock
            "B Y" to 3 + 2, // draw with paper
            "C Y" to 3 + 3, // draw with scissors
            "A Z" to 6 + 2, // win with paper
            "B Z" to 6 + 3, // win with scissors
            "C Z" to 6 + 1, // win with rock
        )
    )

    private fun score(result: Map<String, Int>) = rounds.sumOf { result[it] ?: 0 }
}

fun main() {
    Day2tginsberg("Day2-sample.txt") solution {
        part1() shouldBe 15
        part2() shouldBe 12
    }
    Day2tginsberg("Day2.txt") solution {
        part1() shouldBe 14264
        part2() shouldBe 12382
    }
    Day2tginsberg("Day2-alt.txt") solution {
        part1() shouldBe 13268
        part2() shouldBe 15508
    }
}