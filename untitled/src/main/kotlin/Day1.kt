class Day1(private val fileName: String) : AocSolution {
    override val description: String get() = "Day 1"

    private val input = InputReader(fileName).lines()
    override fun part1() = calories(input, 1)
    override fun part2() = calories(input, 3)

    private fun calories(input: List<String>, n: Int): Int {
        val cals: MutableList<Int> = mutableListOf()
        val maxCals: MutableList<Int> = mutableListOf()
        input.forEach { line ->
            if (line.isNotBlank()) {
                cals.add(line.toInt())
            } else {
                maxCals.add(cals.sumOf { it })
                cals.clear()
            }
        }
        maxCals.add(cals.sumOf { it })
        maxCals.sortDescending()
        return maxCals.subList(0, n).sum()
    }
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
