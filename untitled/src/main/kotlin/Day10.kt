class Day10(private val fileName: String) : AocSolution
{
    override val description: String get() = "Day 10 - Cathode-Ray Tube ($fileName)"

    private val program = InputReader(fileName).lines

    private val registerValues = mutableListOf(1).apply {
        program.forEach { line ->
            add(last())
            if (line.startsWith("addx")) {
                add(last() + line.substringAfter(" ").toInt())
            }
        }
    }.toList()

    private val sampleCycles = (20..220 step 40).toList()

    override fun part1() = sampleSignalStrengths().sum()

    override fun part2() = crtDisplay()

    private fun crtDisplay(): String {
        val crt = Array(6) { CharArray(40) { '.' } }
        registerValues.take(240).forEachIndexed { offset, center ->
            val pixel = offset % 40
            if (pixel in center - 1..center + 1) { crt[offset / 40][pixel] = '#' }
        }
        return crt.joinToString("\n") { it.joinToString(separator = "") }
    }

    private fun sampleSignalStrengths(): List<Int> = registerValues.mapIndexed { i, x ->
        if (sampleCycles.contains(i + 1)) x * (i + 1) else 0
    }
}

fun main() {
    Day10("Day10-sample.txt") solution {
        part1() shouldBe 13140
        part2() shouldBe """
            |##..##..##..##..##..##..##..##..##..##..
            |###...###...###...###...###...###...###.
            |####....####....####....####....####....
            |#####.....#####.....#####.....#####.....
            |######......######......######......####
            |#######.......#######.......#######.....""".trimMargin()
    }
    Day10("Day10.txt") solution {
        part1() shouldBe 11960

        // EJCFPGLH
        part2() shouldBe """
            |####...##..##..####.###...##..#....#..#.
            |#.......#.#..#.#....#..#.#..#.#....#..#.
            |###.....#.#....###..#..#.#....#....####.
            |#.......#.#....#....###..#.##.#....#..#.
            |#....#..#.#..#.#....#....#..#.#....#..#.
            |####..##...##..#....#.....###.####.#..#.""".trimMargin()
    }
}