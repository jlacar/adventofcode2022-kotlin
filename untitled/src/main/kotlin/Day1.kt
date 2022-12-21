class Day1(
    override val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    private val input = InputReader(fileName).lines()
    override fun part1() = Result(expected1, calories(input, 1))
    override fun part2() = Result(expected2, calories(input, 3))
}

fun day1Part1(input: List<String>) = calories(input, 1)

fun day1Part2(input: List<String>) = calories(input, 3).toString()

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
