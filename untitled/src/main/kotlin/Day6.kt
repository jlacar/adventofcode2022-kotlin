class Day6(private val packetSize: Int) {
    fun solution(dataStream: String): Int = dataStream
        .windowed(packetSize, 1)
        .indexOfFirst { isMarker(it) } + packetSize
}

val part1: Day6 = Day6(4)
val part2: Day6 = Day6(14)

fun day6Part1(input: String) = part1.solution(input).toString()

fun day6Part2(input: String) = part2.solution(input).toString()

fun markerOffset(dataStream: String, size: Int) =
    dataStream.windowed(size, 1).indexOfFirst { isMarker(it) } + size

private fun isMarker(packet: String) = packet.toSet().size == packet.length


fun main() {
    println("Part 1 test")
    listOf(
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",
    ).forEach { day6Part1(it).also(::println) }

    println("Part 2 test")
    listOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",
    ).forEach { day6Part2(it).also(::println) }
}