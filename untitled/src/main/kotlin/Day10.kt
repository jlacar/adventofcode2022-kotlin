class Day10(private val fileName: String) : AocSolution
{
    override val description: String get() = "Day 10 - Cathode-Ray Tube ($fileName)"

    private val program = InputReader(fileName).lines()

    private val registerValues = mutableListOf(1).apply {
        program.forEach { line ->
            line.split(" ").let {
                add(last())
                if (it.size == 2) { add(last() + it[1].toInt()) }
            }
        }}.toList()


    private val sampleCycles = (20..220 step 40).toList()

    override fun part1() = sampleSignalStrengths().sum()

    override fun part2() = crtDisplay()

    private fun crtDisplay(): List<String> {
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
    Day10("Day10-sample.txt") shouldHave {
        part1of(13140)
        part2of(
            """##..##..##..##..##..##..##..##..##..##..
              |###...###...###...###...###...###...###.
              |####....####....####....####....####....
              |#####.....#####.....#####.....#####.....
              |######......######......######......####
              |#######.......#######.......#######.....""".trimMargin().lines()
                .onEach(::println)
        )
    }
    Day10("Day10.txt") shouldHave {
        part1of(11960)
        part2of( // EJCFPGLH
            """####...##..##..####.###...##..#....#..#.
              |#.......#.#..#.#....#..#.#..#.#....#..#.
              |###.....#.#....###..#..#.#....#....####.
              |#.......#.#....#....###..#.##.#....#..#.
              |#....#..#.#..#.#....#....#..#.#....#..#.
              |####..##...##..#....#.....###.####.#..#.""".trimMargin().lines()
                .onEach(::println)
        )
    }
}