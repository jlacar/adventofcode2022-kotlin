typealias WorryFunction = (Long) -> Long

class Day11(
    private val fileName: String,
    private val expected1: Long,
    private val expected2: Long
) : Solution {
    override val day: Int get() = 11
    override val source: String get() = "$fileName"

    private fun monkeyTroop() = InputReader(fileName).lines().chunked(7)
        .map { config -> Monkey.parse(config) }

    override fun part1(): Result {
        val monkeys = monkeyTroop()
        monkeys.runRounds(20) { it / 3L }
        return Result(expected1, monkeys.business())
    }

    override fun part2(): Result {
        val monkeys = monkeyTroop()
        val divisorProduct = monkeys.map { it.divisor }.reduce(Long::times)
        monkeys.runRounds(10000) { it % divisorProduct }
        return Result(expected2, monkeys.business() )
    }

    private fun List<Monkey>.runRounds(rounds: Int, manageWorry: WorryFunction) {
        repeat(rounds) {
            this.forEach { monkey -> monkey.takeTurn(this, manageWorry) }
        }
    }
    private fun List<Monkey>.business(): Long {
        val (top1, top2) = this.map { it.inspections }.sortedDescending().take(2)
        return top1.toLong() * top2.toLong()
    }

    class Monkey(
        private val items: MutableList<Item>,
        val divisor: Long,
        val operation: WorryFunction,
        val throwTo: (Long) -> Int
    ) {
        var inspections = 0

        companion object {
            fun parse(config: List<String>): Monkey {
                val items = startingItems(config[1])
                val divisor = config[3].substringAfterLast(" ").toLong()
                val trueMonkey = config[4].substringAfterLast(" ").toInt()
                val falseMonkey = config[5].substringAfterLast(" ").toInt()
                return Monkey(
                    items,
                    divisor,
                    operation = opFun(config[2]),
                    throwTo = ({ level -> if (level % divisor == 0L) trueMonkey else falseMonkey })
                )
            }
            
            private fun opFun(s: String): WorryFunction {
                val value = s.substringAfterLast(" ")
                return when {
                    value == "old" -> ({ level -> level * level })
                    s.contains("+") -> ({ it + value.toLong() })
                    else -> ({ it * value.toLong() })
                }
            }

            private fun startingItems(s: String) = mutableListOf<Item>().apply {
                addAll(s.trim().split(": ")[1].split(", ").map { Item(it.toLong()) })
            }
        }
        
        private fun catch(item: Item) = items.add(item)

        fun takeTurn(otherMonkeys: List<Monkey>, manageWorry: WorryFunction) {
            items.forEach { item ->
                val inspectedItem = item.inspect(operation, manageWorry)
                otherMonkeys[throwTo(inspectedItem.worryLevel)].catch(inspectedItem)
            }
            inspections += items.size
            items.clear()
        }
    }

    data class Item(val worryLevel: Long) {
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
