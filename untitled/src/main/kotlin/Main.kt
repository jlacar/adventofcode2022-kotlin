typealias Solution = (List<String>) -> String

fun main(args: Array<String>) {
    val notYetImplemented = { _: List<String> -> "TBD" }

    solve(1, { input -> day1Part1(input) }, { input -> day1Part2(input) }, "Day1-sample.txt", "Day1.txt")
    solve(2, { input -> day2Part1(input) }, { input -> day2Part2(input) }, "Day2-sample.txt", "Day2.txt")
    solve(3, notYetImplemented, notYetImplemented, "Day3-sample.txt", "Day3.txt")
}

private fun solve(n: Int, part1: Solution, part2: Solution, vararg inputFiles: String) {
    inputFiles.forEach { fileName ->
        println("Day $n ($fileName)")

        val input: List<String> = InputReader(fileName).lines()
        result(1, part1(input))
        result(2, part2(input))
    }
}
fun result(n: Int, result: String) = println(" Part $n: $result")

class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}
