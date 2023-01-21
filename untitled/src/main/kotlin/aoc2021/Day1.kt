package aoc2021

import AocSolution
import InputReader
import shouldBe
import solution

class Day1(private val fileName: String) : AocSolution {

    private val depths = InputReader(fileName).lines.map { it.toInt() }

    override val description: String
        get() = "AoC 2021 - Sonar Sweep ($fileName)"

    override fun part1() = depths.timesGoneDeeper()

    override fun part2() = depths.windowed(3) { it.sum() }.timesGoneDeeper()

    private fun List<Int>.timesGoneDeeper() =
        this.windowed(2).count { (left, right) -> left < right }
}

fun main() {
    Day1("2021/Day1-sample.txt") solution {
        part1() shouldBe 7
        part2() shouldBe 5
    }
    Day1("2021/Day1.txt") solution {
        part1() shouldBe 1390
        part2() shouldBe 1457
    }
}