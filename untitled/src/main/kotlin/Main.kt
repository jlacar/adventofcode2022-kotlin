open class Result(private val expected: Any, private val actual: Any) {
    open fun report(): String = if (actual == expected) "$actual ✅"
        else "❌ expected [$expected] but got [$actual]"
}

val PendingResult = object : Result("?", "?") {
    override fun report() = "⭐ (working on it…)"  // "❗ (pending…)"  //
}

interface Solution {
    val day get() = 0
    val source get() = "Pending"
    val name get() = "Day $day ($source)"
    fun part1() : Result
    fun part2() : Result

    companion object {
        fun report(vararg solution: Solution) {
            solution.forEach {
                println("\n${it.name}")
                println(it.part1().report())
                println(it.part2().report())
            }
        }
    }
}

fun main() {
    Solution.report(






        Day7("Day7-sample.txt", 95437, 24933642),
        Day7("Day7.txt", 1118405, 12545514),
        Day7("Day7-alt.txt", 1886043, 3842121),

        Day8("Day8-sample.txt", 21, 8),
        Day8("Day8.txt", 1690, 535680),
        Day8("Day8-alt.txt", 1698, 672280),
    )
}


