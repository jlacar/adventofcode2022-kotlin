class InputReader(private val path: String) {
    val text = object {}.javaClass.getResource(path)!!.readText().trim()
    val lines = text.lines()
    fun lines() = text.lines()
}

interface AocSolution {
    val description: String get() = "?"
    fun part1() : Any
    fun part2() : Any
}

class DayZ(private val fileName: String) : AocSolution {
    override val description: String get() = "DayZ - test SolutionRunner DSL ($fileName)"

    private val input = InputReader(fileName).lines()

    override fun part1(): String = "String1"

    override fun part2() = input.size
}

class SolutionRunner(private val solution: AocSolution) {
    private fun message(expected: Any, actual: Any) = if (expected == actual)
        "✅ $actual" else "❌ expected [$expected] but got [$actual]"

    fun part1of(expected: Any) {
        message(expected, solution.part1()).also { result -> println("${solution.description} Part 1: $result") }
    }

    fun part2of(expected: Any) {
        message(expected, solution.part2()).also { result -> println("${solution.description} Part 2: $result") }
    }
}

infix fun AocSolution.shouldHave(fn: SolutionRunner.() -> Unit) {
    val runner = SolutionRunner(this)
    runner.fn()
    println()
}

fun main() {
    DayZ("Day1.txt") shouldHave {
        part1of("String2")
        part2of(2255)
    }
}