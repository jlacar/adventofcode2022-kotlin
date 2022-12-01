fun main(args: Array<String>) {
    day1("Day1-sample.txt")
    day1("Day1.txt")

    day2("Day2-sample.txt")
    day2("Day2.txt")

    day3("Day3-sample.txt")
    day3("Day3.txt")
}

private fun solve(n: Int, fileName: String, part1: String, part2: String) {
    day(n, fileName)
    result(1, part1)
    result(2, part2)
}

private fun day1(fileName: String) {
    val input = InputReader(fileName).lines()
    solve(1, fileName, "${day1Part1(input)}", "${day1Part2(input)}")
}

private fun day2(fileName: String) {
    val input = InputReader(fileName).lines()
    solve(2, fileName, "TBD", "TBD")
}

private fun day3(fileName: String) {
    val input = InputReader(fileName).lines()
    solve(3, fileName, "TBD", "TBD")
}

fun day(n: Int, fileName: String) = println("Day $n ($fileName)")

fun result(n: Int, result: String) = println(" Part $n: $result")


class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}