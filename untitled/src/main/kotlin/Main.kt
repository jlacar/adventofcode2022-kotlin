typealias Solution = (List<String>) -> Int

fun main() {
    val notYetImplemented = { _: List<String> -> -1 }

    solve(1, { day1Part1(it) }, { day1Part2(it) }, "Day1-sample.txt|24000|45000", "Day1.txt|71502|208191")
    solve(2, { day2Part1(it) }, { day2Part2(it) }, "Day2-sample.txt|15|12", "Day2.txt|14264|12382")
    solve(3, { day3Part1(it) }, { day3Part2(it) }, "Day3-sample.txt|157|70", "Day3.txt|7446|2646")
    solve(4, { day4Part1(it) }, notYetImplemented, "Day4-sample.txt|1|1" /*, "Day4.txt|1|1" */)
//    solve(5, notYetImplemented, notYetImplemented /*, "Day5-sample.txt|1|1"*//*, "Day5.txt|1|1" */)
//    solve(6, notYetImplemented, notYetImplemented /*, "Day6-sample.txt|1|1"*//*, "Day6.txt|1|1" */)
//    solve(7, notYetImplemented, notYetImplemented /*, "Day7-sample.txt|1|1"*//*, "Day7.txt|1|1" */)
//    solve(8, notYetImplemented, notYetImplemented /*, "Day8-sample.txt|1|1"*//*, "Day8.txt|1|1" */)
//    solve(9, notYetImplemented, notYetImplemented /*, "Day8-sample.txt|1|1"*//*, "Day9.txt|1|1" */)
//    solve(10, notYetImplemented, notYetImplemented /*, "Day10-sample.txt|1|1"*//*, "Day10.txt|1|1" */)
//    solve(11, notYetImplemented, notYetImplemented /*, "Day11-sample.txt|1|1"*//*, "Day11.txt|1|1" */)
//    solve(12, notYetImplemented, notYetImplemented /*, "Day12-sample.txt|1|1"*//*, "Day12.txt|1|1" */)
//    solve(13, notYetImplemented, notYetImplemented /*, "Day13-sample.txt|1|1"*//*, "Day13.txt|1|1" */)
//    solve(14, notYetImplemented, notYetImplemented /*, "Day14-sample.txt|1|1"*//*, "Day14.txt|1|1" */)
//    solve(15, notYetImplemented, notYetImplemented /*, "Day15-sample.txt|1|1"*//*, "Day15.txt|1|1" */)
//    solve(16, notYetImplemented, notYetImplemented /*, "Day16-sample.txt|1|1"*//*, "Day16.txt|1|1" */)
//    solve(17, notYetImplemented, notYetImplemented /*, "Day17-sample.txt|1|1"*//*, "Day17.txt|1|1" */)
//    solve(18, notYetImplemented, notYetImplemented /*, "Day18-sample.txt|1|1"*//*, "Day18.txt|1|1" */)
//    solve(19, notYetImplemented, notYetImplemented /*, "Day19-sample.txt|1|1"*//*, "Day19.txt|1|1" */)
//    solve(20, notYetImplemented, notYetImplemented /*, "Day20-sample.txt|1|1"*//*, "Day20.txt|1|1" */)
//    solve(21, notYetImplemented, notYetImplemented /*, "Day21-sample.txt|1|1"*//*, "Day21.txt|1|1" */)
//    solve(22, notYetImplemented, notYetImplemented /*, "Day22-sample.txt|1|1"*//*, "Day22.txt|1|1" */)
//    solve(23, notYetImplemented, notYetImplemented /*, "Day23-sample.txt|1|1"*//*, "Day23.txt|1|1" */)
//    solve(24, notYetImplemented, notYetImplemented /*, "Day24-sample.txt|1|1"*//*, "Day24.txt|1|1" */)
//    solve(25, notYetImplemented, notYetImplemented /*, "Day25-sample.txt|1|1"*//*, "Day25.txt|1|1" */)
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
    -1 -> "❗ (pending...)"
    0 -> "⭐ (working on it...)"
    else -> if (actual == expected) "$actual ✅"
            else "❌ expected [$expected] but got [$actual]"
}

class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}
