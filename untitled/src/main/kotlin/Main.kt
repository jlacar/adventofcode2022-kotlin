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


