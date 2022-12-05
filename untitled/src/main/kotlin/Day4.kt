fun day4Part1(input: List<String>) = input.count { hasFullContainment(it) }
    .toString()

fun hasFullContainment(it: String): Boolean {
    val (assignment1, assignment2) = it.split(",")
    val section1 = Section(assignment1) //.also(::println)
    val section2 = Section(assignment2) //.also(::println)
    return section1.contains(section2) || section2.contains(section1)
}

fun day4Part2(input: List<String>) = input.count { hasOverlap(it) }
    .toString()

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
