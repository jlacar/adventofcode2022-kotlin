class InputReader(private val path: String) {
    fun lines() = object {}.javaClass.getResource(path)?.readText()!!.lines()
}

interface AocSolution {
    val description: String get() = "?"
    val source: String get() = "Pending"
    fun part1() : Any
    fun part2() : Any
}

class DayZ(private val fileName: String) : AocSolution {
    override val description: String
        get() = "DayZ"
    override val source: String
        get() = "$fileName"

    private val input = InputReader(fileName).lines()

    override fun part1(): String = "String1"

    override fun part2() = input.size
}

class SolutionRunner(private val solution: AocSolution) {
    private fun message(expected: Any, actual: Any) = if (expected == actual)
        "$actual ✅" else "❌ expected [$expected] but got [$actual]"

    private val description: String
        get() = "${solution.description} (${solution.source})"

    fun part1of(expected: Any) {
        message(expected, solution.part1()).also { result -> println("$description Part 1: $result") }
    }

    fun part2of(expected: Any) {
        message(expected, solution.part2()).also { result -> println("$description Part 2: $result") }
    }
}

infix fun AocSolution.shouldHave(fn: SolutionRunner.() -> Unit) {
    val runner = SolutionRunner(this)
    runner.fn()
}

fun main() {
    DayZ("Day1.txt") shouldHave {
        part1of("String2")
        part2of(500)
    }
}