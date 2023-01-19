/* Exercise to go through the thought process for coming up with solution shared by tginsberg */
class Day2tginsberg(private val fileName: String) : AocSolution {
    override val description: String get() = "Day 2 - RPS tginsberg approach ($fileName)"

    private val rounds = InputReader(fileName).lines

    override fun part1() = score(
        mapOf(
            "A X" to 1 + 3, // r vs R -> Draw
            "B X" to 1 + 0, // p vs R -> Lose
            "C X" to 1 + 6, // s vs R -> Win
            "A Y" to 2 + 6, // r vs P -> W
            "B Y" to 2 + 3, // p vs P -> D
            "C Y" to 2 + 0, // s vs P -> L
            "A Z" to 3 + 0, // r vs S -> L
            "B Z" to 3 + 6, // p vs S -> W
            "C Z" to 3 + 3, // s vs S -> D
        )
    )

    override fun part2() = score(
        mapOf(
            "A X" to 0 + 3, // (L) r vs Scissors
            "B X" to 0 + 1, // (L) p vs Rock
            "C X" to 0 + 2, // (L) s vs Paper
            "A Y" to 3 + 1, // (D) r vs R
            "B Y" to 3 + 2, // (D) p vs P
            "C Y" to 3 + 3, // (D) s vs S
            "A Z" to 6 + 2, // (W) r vs P
            "B Z" to 6 + 3, // (W) p vs S
            "C Z" to 6 + 1, // (W) s vs R
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