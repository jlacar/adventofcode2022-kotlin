import java.math.BigInteger

typealias WorryFunction = (BigInteger) -> BigInteger

class Day11(
    private val fileName: String,
    private val expected1: Long,
    private val expected2: Long
) : Solution {
    override val day: Int get() = 11
    override val source: String get() = "$fileName"

    private val monkeyConfig = InputReader(fileName).lines()

    private fun parse(lines: List<String>) = lines.chunked(7).map { config -> Monkey(config) }

    override fun part1(): Result {
        val monkeys = parse(monkeyConfig)
        return Result(expected1, monkeyBusiness(20, monkeys) { it / 3.toBigInteger() })
    }

    override fun part2(): Result {
        val monkeys = parse(monkeyConfig)
        val moduloProduct = monkeys.map { it.modulo }.fold(BigInteger.ONE) { acc, n -> acc.multiply(n) }
        return Result(expected2, monkeyBusiness(10000, monkeys) { it.remainder(moduloProduct) })
    }

    private fun monkeyBusiness(rounds: Int, monkeys: List<Monkey>, manageWorry: WorryFunction): Long {
        repeat(rounds) {
            monkeys.forEach { monkey -> monkey.takeTurn(monkeys, manageWorry) }
        }
        val (top1, top2) = monkeys.map { it.inspections }.sortedDescending().take(2)
        return top1.toLong() * top2.toLong()
    }

    class Monkey(config: List<String>) {
        var inspections = 0
        val modulo: BigInteger

        private val items: MutableList<Item>
        private val operation: WorryFunction
        private val throwTo: (BigInteger) -> Int

        init {
            items = startingItems(config[1])
            operation = opFun(config[2])
            modulo = config[3].trim().split(" ")[3].toBigInteger()
            throwTo = throwFun(modulo, config[4], config[5])
        }

        private fun startingItems(s: String) = mutableListOf<Item>().apply {
            addAll(s.trim().split(": ")[1].split(", ").map { Item(it.toBigInteger()) })
        }

        private fun catch(item: Item) = items.add(item)

        private fun opFun(s: String): WorryFunction {
            val (_, op, n) = s.trim().split(" = ")[1].split(" ")
            fun increaseBy(num: BigInteger) = { item: BigInteger -> item.add(num) }
            fun multiplyBy(num: BigInteger) = { item: BigInteger -> item.multiply(num) }
            return when {
                n == "old" -> { item -> item.multiply(item) }
                op == "+" -> increaseBy(n.toBigInteger())
                else -> multiplyBy(n.toBigInteger())
            }
        }

        private fun throwFun(divisor: BigInteger, toTrue: String, toFalse: String): (BigInteger) -> Int {
            val monkeyTrue = toTrue.trim().split(" ")[5].toInt()
            val monkeyFalse = toFalse.trim().split(" ")[5].toInt()
            return { item -> if (item.remainder(divisor) == BigInteger.ZERO) monkeyTrue else monkeyFalse }
        }

        fun takeTurn(otherMonkeys: List<Monkey>, manageWorry: WorryFunction) {
            items.forEach { item ->
                val inspectedItem = item.inspect(operation, manageWorry)
                otherMonkeys[throwTo(inspectedItem.worryLevel)].catch(inspectedItem)
            }
            inspections += items.size
            items.clear()
        }
    }

    data class Item(val worryLevel: BigInteger) {
        fun inspect(increase: WorryFunction, manage: WorryFunction) = Item(manage(increase(worryLevel)))
    }

}

fun main() {
    Solution.report(
        Day11("Day11-sample.txt", 10605L, 2713310158L),
        Day11("Day11.txt", 111210L, 15447387620L),
        Day11("Day11-alt.txt", 108240L, 25712998901L)
    )
}

/*==================
 * NOTES
 *
 * The trick to part 2 is finding how to better manage the worry level. The problem is that
 * the worry levels get exponentially larger as they keep transforming upon inspection.
 * Part 1 strategy was to divide by 3. Part 2 required you to find a different way.
 *
 * I had to cheat on this one: the magic incantation is to multiply all the "divisible by" numbers
 * of all the monkeys and mod the worry level with it. That is, newlevel = levelAfterInspection % modProduct.
 *
 * Found the Modulo trick info on Reddit.
 * Same approach: (Rust): https://fasterthanli.me/series/advent-of-code-2022/part-11#part-2
 * Other approach?: https://medium.com/@datasciencedisciple/advent-of-code-2022-in-python-day-11-5832b8f25c21
 *
 * Also had a heck of a time figuring out why this was giving different results for what looked like the same
 * code as Day11-alt.kt code, except that one was not wrapped in a class. Problem was a wrong assumption: I
 * thought part 2 started in a clean state when in fact it was starting in the state that part 1 ended in for
 * monkey items. Had to parse the input at the start of each part solution to correctly initialize the items
 * each monkey has at the start.
 */
