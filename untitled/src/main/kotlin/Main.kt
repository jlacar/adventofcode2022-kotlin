open class Result(private val expected: Any, private val actual: Any) {
    open fun report(): String = if (actual == expected) "$actual ✅"
        else "❌ expected [$expected] but got [$actual]"
}

val PendingResult = object : Result("?", "?") {
    override fun report() = "⭐ (working on it...)"  // "❗ (pending...)"  //
}

interface Solution {
    val name: String get() = "Pending solution"
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

    solve(Day7("Day7-sample.txt", 95437, 0))
    solve(Day7("Day7.txt", 1118405, 0))
    solve(Day7("Day7-alt.txt", 1886043, 0))

//    solve(7, pending, pending /*, "Day7-sample.txt|?|?"*//*, "Day7.txt|?|?" */)
//    solve(8, pending, pending /*, "Day8-sample.txt|?|?"*//*, "Day8.txt|?|?" */)
//    solve(9, pending, pending /*, "Day8-sample.txt|?|?"*//*, "Day9.txt|?|?" */)
//    solve(10, pending, pending /*, "Day10-sample.txt|?|?"*//*, "Day10.txt|?|?" */)
//    solve(11, pending, pending /*, "Day11-sample.txt|?|?"*//*, "Day11.txt|?|?" */)
//    solve(12, pending, pending /*, "Day12-sample.txt|?|?"*//*, "Day12.txt|?|?" */)
//    solve(13, pending, pending /*, "Day13-sample.txt|?|?"*//*, "Day13.txt|?|?" */)
//    solve(14, pending, pending /*, "Day14-sample.txt|?|?"*//*, "Day14.txt|?|?" */)
//    solve(15, pending, pending /*, "Day15-sample.txt|?|?"*//*, "Day15.txt|?|?" */)
//    solve(16, pending, pending /*, "Day16-sample.txt|?|?"*//*, "Day16.txt|?|?" */)
//    solve(17, pending, pending /*, "Day17-sample.txt|?|?"*//*, "Day17.txt|?|?" */)
//    solve(18, pending, pending /*, "Day18-sample.txt|?|?"*//*, "Day18.txt|?|?" */)
//    solve(19, pending, pending /*, "Day19-sample.txt|?|?"*//*, "Day19.txt|?|?" */)
//    solve(20, pending, pending /*, "Day20-sample.txt|?|?"*//*, "Day20.txt|?|?" */)
//    solve(21, pending, pending /*, "Day21-sample.txt|?|?"*//*, "Day21.txt|?|?" */)
//    solve(22, pending, pending /*, "Day22-sample.txt|?|?"*//*, "Day22.txt|?|?" */)
//    solve(23, pending, pending /*, "Day23-sample.txt|?|?"*//*, "Day23.txt|?|?" */)
//    solve(24, pending, pending /*, "Day24-sample.txt|?|?"*//*, "Day24.txt|?|?" */)
//    solve(25, pending, pending /*, "Day25-sample.txt|?|?"*//*, "Day25.txt|?|?" */)
}

fun solve(solution: Solution) {
    println("\n${solution.name}")
    println("\tPart 1: ${solution.part1().report()}")
    println("\tPart 2: ${solution.part2().report()}")
}


class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}
