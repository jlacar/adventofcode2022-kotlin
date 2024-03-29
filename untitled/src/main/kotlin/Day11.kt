typealias WorryFunction = (Long) -> Long

class Day11(private val fileName: String) : AocSolution {
    override val description: String get() = "Day 11 - Monkey in the Middle ($fileName)"

    private val input = InputReader(fileName).lines

    private fun monkeyTroop() = input.chunked(7).map { config -> Monkey.parse(config) }

    override fun part1(): Long {
        val monkeys = monkeyTroop()
        monkeys.runRounds(20) { it / 3L }
        return monkeys.business()
    }

    override fun part2(): Long {
        val monkeys = monkeyTroop()
        val divisorProduct = monkeys.map { it.divisor }.reduce(Long::times)
        monkeys.runRounds(10000) { it % divisorProduct }
        return monkeys.business()
    }

    private fun List<Monkey>.runRounds(rounds: Int, manageWorry: WorryFunction) {
        repeat(rounds) {
            forEach { monkey -> monkey.takeTurn(this, manageWorry) }
        }
    }

    private fun List<Monkey>.business(): Long {
        val (first, second) = map { it.inspections }.sortedDescending().take(2)
        return first.toLong() * second.toLong()
    }

    class Item(val worryLevel: Long)

    class Monkey(
        val divisor: Long,
        private val items: MutableList<Item>,
        private val operation: WorryFunction,
        private val pickTarget: (Item) -> Int
    ) {
        var inspections = 0

        companion object {
            fun parse(config: List<String>): Monkey {
                val items = startingItems(config[1].substringAfter(": "))
                val divisor = config[3].substringAfterLast(" ").toLong()
                val trueMonkey = config[4].substringAfterLast(" ").toInt()
                val falseMonkey = config[5].substringAfterLast(" ").toInt()
                return Monkey(
                    divisor,
                    items,
                    operation = opFun(config[2].substringAfter("= old ")),
                    pickTarget = ({ item -> if (item.worryLevel % divisor == 0L) trueMonkey else falseMonkey})
                )
            }

            private fun startingItems(itemCsv: String) = mutableListOf<Item>().apply {
                addAll(itemCsv.split(", ").map { Item(it.toLong()) })
            }

            private fun opFun(s: String): WorryFunction = s.substringAfter(" ").let { value ->
                when {
                    value == "old" -> ({ it * it })
                    s.startsWith("+") -> ({ it + value.toLong() })
                    else -> ({ it * value.toLong() })
                }
            }
        }

        fun takeTurn(troop: List<Monkey>, manageWorry: WorryFunction) {
            items.forEach { inspect(it, manageWorry).also { item -> throwTo(troop, item) } }
            inspections += items.size
            items.clear()
        }

        private fun catch(item: Item) = items.add(item)

        private fun inspect(item: Item, manageWorry: WorryFunction) = Item(manageWorry(operation(item.worryLevel)))

        private fun throwTo(troop: List<Monkey>, item: Item) = troop[pickTarget(item)].catch(item)
    }

}

fun main() {
    Day11("Day11-sample.txt") solution {
        part1() shouldBe 10605L
        part2() shouldBe 2713310158L
    }
    Day11("Day11.txt") solution {
        part1() shouldBe 111210L
        part2() shouldBe 15447387620L
    }
    Day11("Day11-alt.txt") solution {
        part1() shouldBe 108240L
        part2() shouldBe 25712998901L
    }
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
