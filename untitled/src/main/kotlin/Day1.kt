fun day1Part1(input: List<String>) = calories(input, 1).toString()

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
