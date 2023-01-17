import java.math.BigInteger

/**
 * The trick to part 2 solution is finding how to better manage the worry level.
 * Had to cheat on this one: the magic incantation is to multiply all the
 * "divisibly by" numbers and use it as the way to decrease the worry level.
 * Instead of integer division by 3 as in part 1, use level.remainder(n)
 *
 * Modulo trick info found on Reddit
 * Same approach: (Rust): https://fasterthanli.me/series/advent-of-code-2022/part-11#part-2
 * Other approach?: https://medium.com/@datasciencedisciple/advent-of-code-2022-in-python-day-11-5832b8f25c21
 */

class Day11(
    private val fileName: String,
    private val expected1: Long,
    private val expected2: Long) : Solution
{
    override val day: Int get() = 11
    override val source: String get() = "$fileName"

    private val monkeyConfig = InputReader(fileName).lines()

    /*
       DEVELOPER NOTE - had a heck of a time figuring out why this was giving different results for what
       looked like the same exact code, except the working one was not wrapped in a class. Turns out it
       was a wrong assumption: I thought part 2 started in a clean state when in fact it was starting in
       the state that part 1 ended in for monkeys. Had to change so the input is parsed at the start of
       each part solution and you have the monkeys all at the correct start state.
     */

    private fun parse(lines: List<String>) = lines.chunked(7).map { config -> Monkey(config) }

    override fun part1(): Result {
        val monkeys = parse(monkeyConfig)
        return Result(expected1, monkeyBusiness(20, monkeys) { it / 3.toBigInteger() })
    }

    override fun part2(): Result {
        val monkeys = parse(monkeyConfig)
        val modulo = monkeys.map { it.modulo }.fold(BigInteger.ONE) { acc, n -> acc.multiply(n) }
        return Result(expected2, monkeyBusiness(10000, monkeys) { it.remainder(modulo) })
    }

    private fun monkeyBusiness(rounds: Int, monkeys: List<Monkey>, manageWorry: (BigInteger) -> BigInteger): Long {
        repeat (rounds) {
            monkeys.forEach { monkey -> monkey.throwItemsToOther(monkeys, manageWorry) }
        }
        val (top1, top2) = monkeys.map { it.inspections }.sortedDescending().take(2)
        return top1.toLong() * top2.toLong()
    }

    class Monkey(config: List<String>) {
        var inspections = 0
        val modulo: BigInteger

        private val items: MutableList<BigInteger>
        private val operation: (BigInteger) -> BigInteger
        private val throwTo: (BigInteger) -> Int

        init {
            items = startingItems(config[1])
            operation = opFun(config[2])
            modulo = config[3].trim().split(" ")[3].toBigInteger()
            throwTo = throwFun(modulo, config[4], config[5])
        }

        private fun startingItems(s: String) = mutableListOf<BigInteger>().apply {
            addAll(s.trim().split(": ")[1].split(", ").map { it.toBigInteger() })
        }

        private fun catch(item: BigInteger) = items.add(item)

        private fun opFun(s: String): (BigInteger) -> BigInteger {
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

        fun throwItemsToOther(otherMonkeys: List<Monkey>, manageWorry: (BigInteger) -> BigInteger) {
            items.forEach { worryLevel ->
                val newLevel = manageWorry(operation(worryLevel))
                otherMonkeys[throwTo(newLevel)].catch(newLevel)
            }
            inspections += items.size
            items.clear()
        }
    }
}

fun main() {
    Solution.report(
        Day11("Day11-sample.txt", 10605L, 2713310158L),
        Day11("Day11.txt", 111210L, 15447387620L),
        Day11("Day11-alt.txt", 108240L, 25712998901L)
    )
}