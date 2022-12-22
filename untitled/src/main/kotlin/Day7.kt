class Day7(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution {
    override val name: String get() = "Day 7 ($fileName)"

    private val input = InputReader(fileName).lines()
    override fun part1() = PendingResult
    override fun part2() = PendingResult
}

class File (val name: String, val bytes: Int = 0, val isDir: Boolean = true) {
    val contents: MutableList<File> = mutableListOf()
    fun size(): Int = contents.sumOf { f -> f.size() }
}

fun main() {
    val root = File("/")
    val xdir = File("x")
    with (root.contents) {
        add(File("a", 500, true))
        add(File("b", 8000, true))
        add(File("x"))
        add(File("c"))
    }

}