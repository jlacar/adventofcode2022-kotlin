class InputReader(private val path: String) {
    val rawText = object {}.javaClass.getResource(path)!!.readText()
    val rawLines = rawText.lines()
    val text = rawText.trim()
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

class SolutionChecker(private val solution: AocSolution) {
    fun part1() = solution.part1().also { print("Part 1: ") }
    fun part2() = solution.part2().also { print("Part 2: ") }
}

infix fun Any.shouldBe(expected: Any) = println(
    if (this == expected) "✅ $this" else "❌ expected [$expected] but got [$this]"
)

infix fun AocSolution.solution(invoke: SolutionChecker.() -> Unit) {
    println(this.description)
    SolutionChecker(this).invoke()
    println()
}

fun main() {
    DayZ("Day1.txt") solution {
        part1() shouldBe "String2"
        part2() shouldBe 2255
    }
}