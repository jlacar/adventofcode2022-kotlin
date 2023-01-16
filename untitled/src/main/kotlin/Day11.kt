class Day11(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
    val monkeyConfig = InputReader(fileName).lines()

    val monkeys = parse(monkeyConfig)

    private fun parse(lines: List<String>) = lines.chunked(7).map { config -> Monkey(config) }

    override fun part1() = Result(expected1, monkeyBusiness())

    private fun monkeyBusiness(): Int {
        repeat (20) {
            monkeys.forEach { monkey -> monkey.throwItemsToOther(monkeys) }
        }
        return monkeys.map { it.inspections }.sorted().takeLast(2).fold(1) { acc: Int, n: Int -> acc * n }
    }

    override fun part2() = PendingResult

    class Monkey(config: List<String>) {
        private val items: MutableList<Int>
        private val operation: (Int) -> Int
        private val throwTo: (Int) -> Int
        
        var inspections = 0
        
        init {
            items = startingItems(config[1])
            operation = opFun(config[2])
            throwTo = throwFun(config[3], config[4], config[5])
        }

        fun throwItemsToOther(otherMonkeys: List<Monkey>) {
            items.forEach { worryLevel ->
                val newLevel = operation(worryLevel).div(3)
                otherMonkeys[throwTo(newLevel)].catch(newLevel)
            }
            inspections += items.size
            items.clear()
        }

        private fun catch(item: Int) = items.add(item)

        private fun throwFun(divBy: String, toTrue: String, toFalse: String): (Int) -> Int {
            val divisor = divBy.trim().split(" ")[3].toInt()
            val monkeyTrue = toTrue.trim().split(" ")[5].toInt()
            val monkeyFalse = toFalse.trim().split(" ")[5].toInt()
            return { item -> if (item % divisor == 0) monkeyTrue else monkeyFalse }
        }

        private fun opFun(s: String): (Int) -> Int {
            val (_, op, x) = s.trim().split(" = ")[1].split(" ")
            fun multOp(num: Int) = { item: Int -> item * num }
            fun addOp(num: Int) = { item: Int -> item + num }
            return when {
                x == "old" -> { num -> num * num }
                op == "+" -> addOp(x.toInt())
                else -> multOp(x.toInt())
            }
        }

        private fun startingItems(s: String) = mutableListOf<Int>().apply {
            addAll(s.trim().split(": ")[1].split(", ").map { it.toInt() })
        }
    }
}

fun main() {
    Solution.report(
        Day11("Day11-sample.txt", 10605, 0),
        Day11("Day11.txt", 111210, 0)
    )
}