class Day10(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: List<String>) : Solution
{
    override val day: Int get() = 10
    override val source: String get() = "$fileName"

    private val program = InputReader(fileName).lines()

    private val registerValues = mutableListOf(1).apply {
        program.forEach { line ->
            line.split(" ").let {
                add(last())
                if (it.size == 2) { add(last() + it[1].toInt()) }
            }
        }}.toList()


    private val sampleCycles = (20..220 step 40).toList()

    override fun part1() = Result(expected1, sampleSignalStrengths().sum())

    override fun part2() = Result(expected2, crtDisplay())

    fun crtDisplay(): List<String> {
        val crt = Array(6) { CharArray(40) { '.' } }
        registerValues.take(240).forEachIndexed { i, center ->
            val pixel = i % 40
            if (pixel in center-1..center+1) { crt[i/40][pixel] = '#' }
        }
        return crt.map { it.joinToString(separator="") }
    }

    private fun sampleSignalStrengths(): List<Int> = registerValues.mapIndexed { i, x ->
        if (sampleCycles.contains(i + 1)) x * (i + 1) else 0
    }
}

fun main() {
    Solution.report(
        Day10("Day10-sample.txt", 13140,
            """##..##..##..##..##..##..##..##..##..##..
              |###...###...###...###...###...###...###.
              |####....####....####....####....####....
              |#####.....#####.....#####.....#####.....
              |######......######......######......####
              |#######.......#######.......#######.....""".trimMargin().lines()),

        Day10("Day10.txt", 11960,
            """####...##..##..####.###...##..#....#..#.
              |#.......#.#..#.#....#..#.#..#.#....#..#.
              |###.....#.#....###..#..#.#....#....####.
              |#.......#.#....#....###..#.##.#....#..#.
              |#....#..#.#..#.#....#....#..#.#....#..#.
              |####..##...##..#....#.....###.####.#..#.""".trimMargin().lines()).also {
            it.crtDisplay().forEach(::println)  // EJCFPGLH
        },
    )
}