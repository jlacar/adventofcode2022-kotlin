// Solution Template
fun day5Part1(input: List<String>) : String = SolutionStatus.PENDING.toString()

fun day5Part2(input: List<String>) : String = SolutionStatus.PENDING.toString()

class Day5(
    private val fileName: String,
    private val expected1: Any,
    private val expected2: Any) : Solution {
    override val name: String get() = "Day 5 ($fileName)"

    private val input = InputReader(fileName).lines()
    override fun part1() = PendingResult
    override fun part2() = PendingResult
}

class Day5X(input: List<String>) {
    val input = """
        |    [D]
        |[N] [C]
        |[Z] [M] [P]
        | 1   2   3
        | 
        |move 1 from 2 to 1
        |move 3 from 1 to 3
        |move 2 from 2 to 1
        |move 1 from 1 to 2
        """.trimMargin("|")
}

fun main() {

}
