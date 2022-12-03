fun day3Part1(input: List<String>) = input.sumOf { ruckPriority(it) }

fun ruckPriority(line: String): Int {
    val (compartment1, compartment2) = halve(line)
    return priority(commonItemIn(compartment1, compartment2))
}

fun commonItemIn(compartment1: String, compartment2: String): Char {
    val items1 = HashSet(compartment1.toCharArray().toList())
    val items2 = HashSet(compartment2.toCharArray().toList())
    return items1.intersect(items2).first()
}

fun halve(line: String): Pair<String, String> {
    val half = line.length / 2
    return Pair(line.substring(0, half), line.substring(half))
}

fun priority(ch: Char) =
    ch.lowercaseChar() - 'a' + (if (ch.isLowerCase()) 1 else 27)

fun day3Part2(input: List<String>) = 0
