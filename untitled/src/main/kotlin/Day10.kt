class Day10(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    override val day: Int get() = 10
    override val source: String get() = "$fileName"

    private val program = InputReader(fileName).lines()

    private val sampleCycles = (20..220 step 40).toList()

    override fun part1() = PendingResult // Result(expected1, signalStrengths() sum())

    private fun signalStrengths(): MutableList<Int> {
//        var register = 1
//        program.foldIndexed(mutableListOf(1)) { i, signalStrengths, line ->
//            when {
//                line.startsWith("noop")
//            }
//        }
        return mutableListOf()
    }

    override fun part2() = PendingResult
}


fun main() {
//    Solution.report(
//        Day10("Day10-sample.txt", 13140, 0),
//        Day10("Day10.txt", 0, 0)
//    )

}