typealias Solution = (List<String>) -> Int

fun main(args: Array<String>) {
    val notYetImplemented = { _: List<String> -> 0 }

    solve(1, { day1Part1(it) }, { day1Part2(it) }, "Day1-sample.txt", "Day1.txt")
    solve(2, { day2Part1(it) }, { day2Part2(it) }, "Day2-sample.txt", "Day2.txt")
    solve(3, notYetImplemented, notYetImplemented, "Day3-sample.txt", "Day3.txt")
    solve(4, notYetImplemented, notYetImplemented /*, "Day4-sample.txt", "Day4.txt" */)
    solve(5, notYetImplemented, notYetImplemented /*, "Day5-sample.txt", "Day5.txt" */)
    solve(6, notYetImplemented, notYetImplemented /*, "Day6-sample.txt", "Day6.txt" */)
    solve(7, notYetImplemented, notYetImplemented /*, "Day7-sample.txt", "Day7.txt" */)
}

private fun solve(n: Int, part1: Solution, part2: Solution, vararg inputFiles: String) {
    inputFiles.forEach { fileName ->
        println("Day $n ($fileName)")

        val input: List<String> = InputReader(fileName).lines()
        result(1, part1(input))
        result(2, part2(input))
    }
}
private fun result(n: Int, result: Int) = println(" Part $n: $result")

class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}
