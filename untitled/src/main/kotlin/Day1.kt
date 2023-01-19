class Day1(private val fileName: String) : AocSolution {
    override val description: String get() = "Day 1 - Calorie Counting ($fileName)"

    private val calories = InputReader(fileName).text
        .split("\n\n")
        .map { it.lines().sumOf { n -> n.toInt() } }

    override fun part1() = calories.max()
    override fun part2() = calories.sorted().takeLast(3).sum()
}

fun main() {
    Day1("Day1-sample.txt") shouldHave {
        part1of(24000)
        part2of(45000)
    }
    Day1("Day1.txt") shouldHave {
        part1of(71502)
        part2of(208191)
    }
    Day1("Day1-alt.txt") shouldHave {
        part1of(69836)
        part2of(207968)
    }
}