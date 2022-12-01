import java.lang.Integer.max

fun day1Part2(input: List<String>): Int {
    val cals: MutableList<Int> = ArrayList<Int>()
    val maxCals: MutableList<Int> = ArrayList<Int>()
    input.forEach { l ->
        if (l.isEmpty()) {
            maxCals.add(cals.sumOf { it} )
            cals.clear()
        } else {
            cals.add(l.toInt())
        }
    }
    maxCals.sortDescending()
    return maxCals.subList(0, 3).sum()
}

fun day1Part1(input: List<String>): Int {
    var cals = 0
    var maxCals = 0
    input.forEach { l ->
        if (l.isEmpty()) {
            maxCals = max(maxCals, cals)
            cals = 0
        } else {
            cals += l.toInt()
        }
    }
    return maxCals
}
