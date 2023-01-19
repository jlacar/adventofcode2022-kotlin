open class Result(private val expected: Any, private val actual: Any) {
    open fun report(): String = if (actual == expected) "$actual ✅"
        else "❌ expected [$expected] but got [$actual]"
}

val PendingResult = object : Result("?", "?") {
    override fun report() = "⭐ (working on it…)"  // "❗ (pending…)"  //
}

interface Solution {
    val day get() = 0
    val source get() = "Pending"
    val name get() = "Day $day ($source)"
    fun part1() : Result
    fun part2() : Result

    companion object {
        fun report(vararg solution: Solution) {
            solution.forEach {
                println("\n${it.name}")
                println(it.part1().report())
                println(it.part2().report())
            }
        }
    }
}

fun main() {
    Solution.report(
        Day2("Day2-sample.txt", 15, 12),
        Day2("Day2.txt", 14264, 12382),
        Day2("Day2-alt.txt", 13268, 15508),

        Day3("Day3-sample.txt", 157, 70),
        Day3("Day3.txt", 7446, 2646),
        Day3("Day3-alt.txt", 8349, 2681),

        Day4("Day4-sample.txt", 2, 4),
        Day4("Day4.txt", 471, 888),
        Day4("Day4-alt.txt", 507, 897),

        Day5("Day5-sample.txt", "CMZ", "MCD"),
        Day5("Day5.txt", "MQTPGLLDN", "LVZPSTTCZ"),
        Day5("Day5-alt.txt", "HBTMTBSDC", "PQTJRSHWS"),

        Day6("Day6.txt", 1093, 3534),
        Day6("Day6-alt.txt", 1155, 2789),

        Day7("Day7-sample.txt", 95437, 24933642),
        Day7("Day7.txt", 1118405, 12545514),
        Day7("Day7-alt.txt", 1886043, 3842121),

        Day8("Day8-sample.txt", 21, 8),
        Day8("Day8.txt", 1690, 535680),
        Day8("Day8-alt.txt", 1698, 672280),
    )
}


