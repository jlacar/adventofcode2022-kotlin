import java.lang.Integer.max

fun day1Part1(input: List<String>) = calories(input, 1)

fun day1Part2(input: List<String>) = calories(input, 3)

private fun calories(input: List<String>, n: Int): String {
    val cals: MutableList<Int> = ArrayList<Int>()
    val maxCals: MutableList<Int> = ArrayList<Int>()
    input.forEach { l ->
        if (l.isEmpty()) {
            maxCals.add(cals.sumOf { it })
            cals.clear()
        } else {
            cals.add(l.toInt())
        }
    }
    maxCals.sortDescending()
    return maxCals.subList(0, n).sum().toString()
}


fun day3Part1(input: List<String>): String {
    return "Not implemented"
}

fun day3Part2(input: List<String>): String {
    return "Not implemented"
}