fun day3Part1(input: List<String>) = input.sumOf { ruckPriority(it) }

fun ruckPriority(line: String): Int {
    val (compartment1, compartment2) = halve(line)
    return priority(commonItemIn(compartment1, compartment2))
}

fun day3Part2(input: List<String>) = input
    .chunked(3).sumOf { groupBadgePriority(it) }

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

fun priority(ch: Char) =
    ch.lowercaseChar() - 'a' + (if (ch.isLowerCase()) 1 else 27)
