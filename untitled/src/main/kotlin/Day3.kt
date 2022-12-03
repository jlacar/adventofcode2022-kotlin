import javax.swing.text.MutableAttributeSet

fun day3Part1(input: List<String>) = input.sumOf { ruckPriority(it) }

fun ruckPriority(line: String): Int {
    val (compartment1, compartment2) = halve(line)
    return priority(commonItemIn(compartment1, compartment2))
}

fun day3Part2(input: List<String>) = sumOfBadgePriority(input)

fun sumOfBadgePriority(input: List<String>): Int {
    val group: MutableList<Set<Char>> = mutableListOf()
    val priorities: MutableList<Int> = mutableListOf()
    input.forEach { items ->
        group.add(items.toSet())
        if (group.size == 3) {
            priorities.add(badgePriorityOf(group))
            group.clear()
        }
    }
    return priorities.sum()
}

fun badgePriorityOf(group: MutableList<Set<Char>>): Int {
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
