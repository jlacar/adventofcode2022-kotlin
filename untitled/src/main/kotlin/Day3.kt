class Day3(private val fileName: String) : AocSolution {
    override val description: String get() = "Day 3 - Rucksack Reorg ($fileName)"

    private val input = InputReader(fileName).lines
    override fun part1() = input.sumOf { ruckPriority(it) }
    override fun part2() = input.chunked(3).sumOf { groupBadgePriority(it) }
}

fun ruckPriority(line: String): Int {
    val (compartment1, compartment2) = halve(line)
    return priority(commonItemIn(compartment1, compartment2))
}

fun groupBadgePriority(input: List<String>) =
    badgePriority( input.map { it.toSet() } )

fun badgePriority(group: List<Set<Char>>): Int {
    val (elf1, elf2, elf3) = group
    return priority(elf1.intersect(elf2).intersect(elf3).first())
}

fun commonItemIn(compartment1: String, compartment2: String) =
    compartment1.toSet().intersect(compartment2.toSet()).first()

fun halve(line: String): Pair<String, String> {
    val half = line.length / 2
    return Pair(line.substring(0, half), line.substring(half))
}

private const val lowercase_a_priority = 1;
private const val UPPERCASE_A_priority = 27;
fun priority(ch: Char) = ch.lowercaseChar() - 'a' +
    if (ch.isLowerCase()) lowercase_a_priority else UPPERCASE_A_priority

fun main() {
    Day3("Day3-sample.txt") shouldHave {
        part1of(157)
        part2of(70)
    }
    Day3("Day3.txt") shouldHave {
        part1of(7446)
        part2of(2646)
    }
    Day3("Day3-alt.txt") shouldHave {
        part1of(8349)
        part2of(2681)
    }
}