typealias Solution = (List<String>) -> String

enum class SolutionStatus {
    PENDING, SOLVING
}

fun main() {
    val pending = { _ : Any -> SolutionStatus.PENDING.toString() }
    val solving = { _ : Any -> SolutionStatus.SOLVING.toString() }

//    solve(25, pending, pending /*, "Day25-sample.txt|?|?"*//*, "Day25.txt|?|?" */)
//    solve(24, pending, pending /*, "Day24-sample.txt|?|?"*//*, "Day24.txt|?|?" */)
//    solve(23, pending, pending /*, "Day23-sample.txt|?|?"*//*, "Day23.txt|?|?" */)
//    solve(22, pending, pending /*, "Day22-sample.txt|?|?"*//*, "Day22.txt|?|?" */)
//    solve(21, pending, pending /*, "Day21-sample.txt|?|?"*//*, "Day21.txt|?|?" */)
//    solve(20, pending, pending /*, "Day20-sample.txt|?|?"*//*, "Day20.txt|?|?" */)
//    solve(19, pending, pending /*, "Day19-sample.txt|?|?"*//*, "Day19.txt|?|?" */)
//    solve(18, pending, pending /*, "Day18-sample.txt|?|?"*//*, "Day18.txt|?|?" */)
//    solve(17, pending, pending /*, "Day17-sample.txt|?|?"*//*, "Day17.txt|?|?" */)
//    solve(16, pending, pending /*, "Day16-sample.txt|?|?"*//*, "Day16.txt|?|?" */)
//    solve(15, pending, pending /*, "Day15-sample.txt|?|?"*//*, "Day15.txt|?|?" */)
//    solve(14, pending, pending /*, "Day14-sample.txt|?|?"*//*, "Day14.txt|?|?" */)
//    solve(13, pending, pending /*, "Day13-sample.txt|?|?"*//*, "Day13.txt|?|?" */)
//    solve(12, pending, pending /*, "Day12-sample.txt|?|?"*//*, "Day12.txt|?|?" */)
//    solve(11, pending, pending /*, "Day11-sample.txt|?|?"*//*, "Day11.txt|?|?" */)
//    solve(10, pending, pending /*, "Day10-sample.txt|?|?"*//*, "Day10.txt|?|?" */)
//    solve(9, pending, pending /*, "Day8-sample.txt|?|?"*//*, "Day9.txt|?|?" */)
//    solve(8, pending, pending /*, "Day8-sample.txt|?|?"*//*, "Day8.txt|?|?" */)
//    solve(7, pending, pending /*, "Day7-sample.txt|?|?"*//*, "Day7.txt|?|?" */)
    solve(6, { day6Part1(it.first()) }, { day6Part2(it.first()) }/*, "Day6-sample.txt|?|?"*/, "Day6.txt|1093|3534" )
    solve(5, { day5Part1(it) }, pending , "Day5-sample.txt|CMZ|?"/*, "Day5.txt|?|?" */)
    solve(4, { day4Part1(it) }, { day4Part2(it) }, "Day4-sample.txt|2|4", "Day4.txt|471|888" )
    solve(3, { day3Part1(it) }, { day3Part2(it) }, "Day3-sample.txt|157|70", "Day3.txt|7446|2646")
    solve(2, { day2Part1(it) }, { day2Part2(it) }, "Day2-sample.txt|15|12", "Day2.txt|14264|12382")
    solve(1, { day1Part1(it) }, { day1Part2(it) }, "Day1-sample.txt|24000|45000", "Day1.txt|71502|208191")
}

private fun solve(n: Int, part1: Solution, part2: Solution, vararg params: String) {
    params.forEach { param ->
        val (fileName, expected1, expected2) = param.split("|")
        val input: List<String> = InputReader(fileName).lines()

        println("\nDay $n ($fileName)")
        report(1, part1(input), expected1)
        report(2, part2(input), expected2)
    }
}

private fun report(n: Int, actual: String, expected: String) =
    println("\tPart $n: ${status(expected, actual)}")

private fun status(expected: String, actual: String) = when (actual) {
    SolutionStatus.PENDING.toString() -> "❗ (pending...)"
    SolutionStatus.SOLVING.toString(), "?" -> "⭐ (working on it...)"
    else -> if (actual == expected) "$actual ✅"
            else "❌ expected [$expected] but got [$actual]"
}

class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}
