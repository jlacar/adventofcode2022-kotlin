typealias Solution = (List<String>) -> Int

object SolutionStatus {
    const val PENDING = -1
    const val SOLVING = 0
}

fun main() {
    val pending = { _ : Any -> SolutionStatus.PENDING }
    val solving = { _ : Any -> SolutionStatus.SOLVING }

    solve(1, { day1Part1(it) }, { day1Part2(it) }, "Day1-sample.txt|24000|45000", "Day1.txt|71502|208191")
    solve(2, { day2Part1(it) }, { day2Part2(it) }, "Day2-sample.txt|15|12", "Day2.txt|14264|12382")
    solve(3, { day3Part1(it) }, { day3Part2(it) }, "Day3-sample.txt|157|70", "Day3.txt|7446|2646")
    solve(4, solving, pending, "Day4-sample.txt|1|1" /*, "Day4.txt|1|1" */)
//    solve(5, pending, pending /*, "Day5-sample.txt|1|1"*//*, "Day5.txt|1|1" */)
//    solve(6, pending, pending /*, "Day6-sample.txt|1|1"*//*, "Day6.txt|1|1" */)
//    solve(7, pending, pending /*, "Day7-sample.txt|1|1"*//*, "Day7.txt|1|1" */)
//    solve(8, pending, pending /*, "Day8-sample.txt|1|1"*//*, "Day8.txt|1|1" */)
//    solve(9, pending, pending /*, "Day8-sample.txt|1|1"*//*, "Day9.txt|1|1" */)
//    solve(10, pending, pending /*, "Day10-sample.txt|1|1"*//*, "Day10.txt|1|1" */)
//    solve(11, pending, pending /*, "Day11-sample.txt|1|1"*//*, "Day11.txt|1|1" */)
//    solve(12, pending, pending /*, "Day12-sample.txt|1|1"*//*, "Day12.txt|1|1" */)
//    solve(13, pending, pending /*, "Day13-sample.txt|1|1"*//*, "Day13.txt|1|1" */)
//    solve(14, pending, pending /*, "Day14-sample.txt|1|1"*//*, "Day14.txt|1|1" */)
//    solve(15, pending, pending /*, "Day15-sample.txt|1|1"*//*, "Day15.txt|1|1" */)
//    solve(16, pending, pending /*, "Day16-sample.txt|1|1"*//*, "Day16.txt|1|1" */)
//    solve(17, pending, pending /*, "Day17-sample.txt|1|1"*//*, "Day17.txt|1|1" */)
//    solve(18, pending, pending /*, "Day18-sample.txt|1|1"*//*, "Day18.txt|1|1" */)
//    solve(19, pending, pending /*, "Day19-sample.txt|1|1"*//*, "Day19.txt|1|1" */)
//    solve(20, pending, pending /*, "Day20-sample.txt|1|1"*//*, "Day20.txt|1|1" */)
//    solve(21, pending, pending /*, "Day21-sample.txt|1|1"*//*, "Day21.txt|1|1" */)
//    solve(22, pending, pending /*, "Day22-sample.txt|1|1"*//*, "Day22.txt|1|1" */)
//    solve(23, pending, pending /*, "Day23-sample.txt|1|1"*//*, "Day23.txt|1|1" */)
//    solve(24, pending, pending /*, "Day24-sample.txt|1|1"*//*, "Day24.txt|1|1" */)
//    solve(25, pending, pending /*, "Day25-sample.txt|1|1"*//*, "Day25.txt|1|1" */)
}

private fun solve(n: Int, part1: Solution, part2: Solution, vararg params: String) {
    params.forEach { param ->
        val (fileName, expected1, expected2) = param.split("|")
        val input: List<String> = InputReader(fileName).lines()

        println("\nDay $n ($fileName)")
        report(1, part1(input), expected1.toInt())
        report(2, part2(input), expected2.toInt())
    }
}

private fun report(n: Int, actual: Int, expected: Int) =
    println("\tPart $n: ${status(expected, actual)}")

private fun status(expected: Int, actual: Int) = when (actual) {
    SolutionStatus.PENDING -> "❗ (pending...)"
    SolutionStatus.SOLVING -> "⭐ (working on it...)"
    else -> if (actual == expected) "$actual ✅"
            else "❌ expected [$expected] but got [$actual]"
}

class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}
