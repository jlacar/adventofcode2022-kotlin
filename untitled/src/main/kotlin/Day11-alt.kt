import java.math.BigInteger

val sample = InputReader("Day11-sample.txt").lines
val input = InputReader("Day11-alt.txt").lines

val monkeys = parse(input)
val bigModulo: BigInteger = monkeys.map { it.modulo }.fold(BigInteger.ONE) { acc, prime -> acc.multiply(prime) }
    //.also { println("bigModulo $it") }

fun parse(lines: List<String>) = lines.chunked(7).map { Monkey(it) }

fun main() {
    repeat(10000) {
        monkeys.forEach { monkey -> monkey.throwItemsToOther(monkeys) { it.remainder(bigModulo) } }
    }
    val (a, b) = monkeys.map { it.inspections }.sorted().takeLast(2)
    println("$a * $b = ${a.toLong() * b.toLong()}")
}

class Monkey(info: List<String>) {
    val items: MutableList<BigInteger>
    val modulo: BigInteger
    val operation: (BigInteger) -> BigInteger
    val throwTo: (BigInteger) -> Int

    var inspections = 0

    init {
        items = startingItems(info[1].trim())
        operation = opFun(info[2].trim())
        modulo = divisibleBy(info[3])
        throwTo = throwFun(modulo, info[4].trim(), info[5].trim())
    }

    private fun startingItems(line: String) = mutableListOf<BigInteger>().apply {
        addAll(line.split(": ")[1].split(", ").map { it.toBigInteger() })
    }

    private fun opFun(line: String): (BigInteger) -> BigInteger {
        val (_, op, x) = line.split(" = ")[1].split(" ")
        fun mult(num: BigInteger) = { worryLevel: BigInteger -> worryLevel.multiply(num) }
        fun incr(num: BigInteger) = { worryLevel: BigInteger -> worryLevel.add(num) }
        return when {
            x == "old" -> { num -> num.multiply(num) }
            op == "+" -> incr(x.toBigInteger())
            else -> mult(x.toBigInteger())
        }
    }

    private fun divisibleBy(line: String) = line.trim().split(" ")[3].toBigInteger()

    private fun throwFun(divisor: BigInteger, toTrue: String, toFalse: String): (BigInteger) -> Int {
        val monkeyTrue = toTrue.split(" ")[5].toInt()
        val monkeyFalse = toFalse.split(" ")[5].toInt()
        return { item -> if (item.remainder(divisor) == BigInteger.ZERO) monkeyTrue else monkeyFalse }
    }

    fun catch(item: BigInteger) = items.add(item)

    fun throwItemsToOther(otherMonkeys: List<Monkey>, manageWorry: (BigInteger) -> BigInteger) {
        inspections += items.size
        items.forEach { worryLevel ->
            val newLevel = manageWorry(operation(worryLevel)) // operation(worryLevel).remainder(bigModulo)
            otherMonkeys[throwTo(newLevel)].catch(newLevel)
        }
        items.clear()
    }

    override fun toString() = "$items"
}