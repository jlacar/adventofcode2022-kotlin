typealias Solution = (List<String>) -> Int

fun main(args: Array<String>) {
    val notYetImplemented = { _: List<String> -> -1 }

    solve(1, { day1Part1(it) }, { day1Part2(it) }, "Day1-sample.txt|24000|45000", "Day1.txt|71502|208191")
    solve(2, { day2Part1(it) }, { day2Part2(it) }, "Day2-sample.txt|15|12", "Day2.txt|14264|12382")
    solve(3, { day3Part1(it) }, { day3Part2(it) }, "Day3-sample.txt|157|70", "Day3.txt|7446|2646")
    solve(4, notYetImplemented, notYetImplemented /*, "Day4-sample.txt|1|1", "Day4.txt|1|1" */)
    solve(5, notYetImplemented, notYetImplemented /*, "Day5-sample.txt|1|1", "Day5.txt|1|1" */)
    solve(6, notYetImplemented, notYetImplemented /*, "Day6-sample.txt|1|1", "Day6.txt|1|1" */)
    solve(7, notYetImplemented, notYetImplemented /*, "Day7-sample.txt|1|1", "Day7.txt|1|1" */)
}

private fun solve(n: Int, part1: Solution, part2: Solution, vararg params: String) {
    params.forEach { param ->
        val (fileName, expected1, expected2) = param.split("|")
        val input: List<String> = InputReader(fileName).lines()

        println("Day $n ($fileName)")
        result(1, part1(input), expected1.toInt())
        result(2, part2(input), expected2.toInt())
    }
}

private fun result(n: Int, result: Int, expected: Int) = 
    println(" Part $n: $result ${mark(expected, result)}")

private fun mark(expected: Int, actual: Int) = 
    if (actual == expected) "✅" else "❌- expected [$expected]"

class InputReader(private val fileName: String) {
    fun lines() = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}
