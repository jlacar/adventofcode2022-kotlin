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
}

fun main() {
    solve(Day1("Day1-sample.txt", 24000, 45000))
    solve(Day1("Day1.txt", 71502, 208191))
    solve(Day1("Day1-alt.txt", 69836, 207968))

    solve(Day2("Day2-sample.txt", 15, 12))
    solve(Day2("Day2.txt", 14264, 12382))
    solve(Day2("Day2-alt.txt", 13268, 15508))

    solve(Day3("Day3-sample.txt", 157, 70))
    solve(Day3("Day3.txt", 7446, 2646))
    solve(Day3("Day3-alt.txt", 8349, 2681))

    solve(Day4("Day4-sample.txt", 2, 4))
    solve(Day4("Day4.txt", 471, 888))
    solve(Day4("Day4-alt.txt", 507, 897))

    solve(Day5("Day5-sample.txt", "CMZ", "MCD"))
    solve(Day5("Day5.txt", "MQTPGLLDN", "LVZPSTTCZ"))
    solve(Day5("Day5-alt.txt", "HBTMTBSDC", "PQTJRSHWS"))

    solve(Day6("Day6.txt", 1093, 3534))
    solve(Day6("Day6-alt.txt", 1155, 2789))

    solve(Day7("Day7-sample.txt", 95437, 24933642))
    solve(Day7("Day7.txt", 1118405, 12545514))
    solve(Day7("Day7-alt.txt", 1886043, 3842121))

//    solve(Day8("Day8-test.txt", 21, 0))
    solve(Day8("Day8-sample.txt", 21, 0))
    solve(Day8("Day8.txt", 1690, 0))
    solve(Day8("Day8-alt.txt", 1698, 0))
}

fun solve(solution: Solution) {
    println("\n${solution.name}")
    println("\tPart 1: ${solution.part1().report()}")
    println("\tPart 2: ${solution.part2().report()}")
}


class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}
