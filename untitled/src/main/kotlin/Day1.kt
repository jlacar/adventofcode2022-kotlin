fun day1Part1(input: List<String>) = calories(input, 1)

fun day1Part2(input: List<String>) = calories(input, 3)

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
    maxCals.sortDescending()
    return maxCals.subList(0, n).sum()
}
