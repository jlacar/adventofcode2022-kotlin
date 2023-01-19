class Day4(private val fileName: String) : AocSolution {
    override val description: String get() = "Day 4 - Camp Cleanup ($fileName)"

    private val input = InputReader(fileName).lines
    override fun part1() = input.count { hasFullContainment(it) }
    override fun part2() = input.count { hasOverlap(it) }
}


fun hasFullContainment(it: String): Boolean {
    val (assignment1, assignment2) = it.split(",")
    val section1 = Section(assignment1) //.also(::println)
    val section2 = Section(assignment2) //.also(::println)
    return section1.contains(section2) || section2.contains(section1)
}

fun hasOverlap(it: String): Boolean {
    val (assignment1, assignment2) = it.split(",")
    return Section(assignment1).overlaps(Section(assignment2))
}

class Section(specifier: String) {
    private val range: IntRange
    init {
        val (from, to) = specifier.split("-")
        range = from.toInt()..to.toInt()
    }

    fun contains(other: Section) =
        other.range.first in range && other.range.last in range

    fun overlaps(other: Section) =
        range.first <= other.range.last && range.last >= other.range.first

    override fun toString(): String = range.toString()
}

fun main() {
    Day4("Day4-sample.txt") solution {
        part1() shouldBe 2
        part2() shouldBe 4
    }
    Day4("Day4.txt") solution {
        part1() shouldBe 471
        part2() shouldBe 888
    }
    Day4("Day4-alt.txt") solution {
        part1() shouldBe 507
        part2() shouldBe 897
    }
}