class Day5(
    private val fileName: String,
    private val expected1: String,
    private val expected2: String) : Solution {
    override val name: String get() = "Day 5 ($fileName)"

    private val input = InputReader(fileName).lines().filter { it.isNotBlank() }
    override fun part1() = Result(expected1, Day5X(input).solve())
    override fun part2() = Result(expected2, Day5X(input).solve2())
}

class Day5X(val input: List<String> = """
        |    [D]
        |[N] [C]
        |[Z] [M] [P]
        | 1   2   3
        | 
        |move 1 from 2 to 1
        |move 3 from 1 to 3
        |move 2 from 2 to 1
        |move 1 from 1 to 2
        """.trimMargin("|").lines().filter { it.isNotBlank() }) {
    fun solve(): String {
        val crates = input.filter { it.trim().first() == '[' }
        val stacks = createStacks(input.first { it.trim().first() == '1' })
        val moves = input.filter { it.trim().first() == 'm' }

        crates.reversed().forEach { layer -> addTo(stacks, layer) }
        moves.forEach { op -> perform(op, stacks) }
        return stacks.fold("") { acc, stack -> acc + stack.peek() }
    }

    fun solve2(): String {
        val crates = input.filter { it.trim().first() == '[' }
        val stacks = createStacks(input.first { it.trim().first() == '1' })
        val moves = input.filter { it.trim().first() == 'm' }

        crates.reversed().forEach { layer -> addTo(stacks, layer) }
        moves.forEach { op -> perform1(op, stacks) }
        return stacks.fold("") { acc, stack -> acc + stack.peek() }
    }

    private fun perform(operation: String, stacks: List<Stack<Char>>) {
        val (unused, count, frStack, toStack) = operation.split("""move | from | to """.toRegex())
        (   1..count.toInt()).forEach { _ -> stacks[toStack.toInt()-1].push(stacks[frStack.toInt()-1].pop()) }
    }
    private fun perform1(operation: String, stacks: List<Stack<Char>>) {
        val (count, frStack, toStack) = moves(operation.split("""move | from | to """.toRegex()))
        val tempStack = Stack<Char>()
        (1..count).forEach { _ -> tempStack.push(stacks[frStack].pop()) }
        (1..tempStack.height).forEach { _ -> stacks[toStack].push(tempStack.pop()) }
    }

    private fun moves(params: List<String>) = intArrayOf(params[1].toInt(), params[2].toInt()-1, params[3].toInt()-1)

    private fun addTo(stacks: List<Stack<Char>>, layer: String) {
        for (i in stacks.indices) {
            (4 * i + 1).takeIf { it < layer.length }?.also { offset ->
                layer.substring(offset).first().takeIf { ch -> ch != ' ' }?.also {
                    ch -> stacks[i].push(ch)
                }
            }
        }
    }

    private fun createStacks(stackList: String): List<Stack<Char>> {
        val stacks = mutableListOf<Stack<Char>>()
        stackList.trim().split("""\s+""".toRegex()).forEach { _ -> stacks.add(Stack()) }
        return stacks
    }
}

fun main() {
    val day5 = Day5X()
    println(day5.solve())
    println(day5.solve2())
}

class Stack<E> {
    private val stack: ArrayDeque<E> = ArrayDeque()
    val height: Int get() = stack.size

    fun push(element: E) = stack.add(element)

    fun pop(): E = stack.removeLast()

    fun peek(): E? = stack.lastOrNull()

    fun isNotEmpty() = stack.isNotEmpty()

    override fun toString(): String {
        return stack.toString()
    }
}
