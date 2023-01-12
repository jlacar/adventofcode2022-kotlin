class Day10(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    override val day: Int get() = 10
    override val source: String get() = "$fileName"

    private val program = InputReader(fileName).lines()

    private val sampleCycles = (20..220 step 40).toList()

    override fun part1() = Result(expected1, sampleSignalStrengths().sum())

    private fun sampleSignalStrengths(): List<Int> = registerValues().mapIndexed { i, x ->
        if (sampleCycles.contains(i + 1)) x * (i + 1) else 0
    }

    private fun registerValues(): List<Int> =
        mutableListOf(1).also { values ->
            program.forEach { instruction ->
                instruction.split(" ").let { parts ->
                    values.add(values.last())
                    if (parts.size == 2) {
                        values.add(values.last() + parts[1].toInt())
                    }
                }
            }
        }.toList()

    override fun part2() = PendingResult
}

fun main() {
    Solution.report(
        Day10("Day10-sample.txt", 13140, 0),
        Day10("Day10.txt", 11960, 0)
    )
}