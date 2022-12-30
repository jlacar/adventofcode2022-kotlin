class Day4(
    private val fileName: String,
    private val expected1: Any,
    private val expected2: Any) : Solution {

    override val day get() = 4
    override val source get() = "$fileName"

    private val input = InputReader(fileName).lines()
    override fun part1() = Result(expected1, input.count { hasFullContainment(it) })
    override fun part2() = Result(expected2, input.count { hasOverlap(it) })
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
