class Day6(
    private val fileName: String,
    private val expected1: Int = 0,
    private val expected2: Int = 0) : Solution {
    override val name: String get() = "Day 6 ($fileName)"

    private val input = InputReader(fileName).lines()
    override fun part1() = Result(expected1, solve(input.first(), 4))
    override fun part2() = Result(expected2, solve(input.first(), 14))
}

private fun solve(dataStream: String, packetSize: Int) =
    dataStream.windowed(packetSize, 1).indexOfFirst { isMarker(it) } + packetSize

private fun isMarker(packet: String) = packet.toSet().size == packet.length

fun main() {
    println("Part 1 test")
    mapOf(
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
        "nppdvjthqldpwncqszvftbrmjlhg" to 6,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11,
    ).forEach { (input, expected) -> Result(expected, solve(input, 4))
        .also { println("\t[$input] : ${it.report()}") }}

    println("Part 2 test")
    mapOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
        "nppdvjthqldpwncqszvftbrmjlhg" to 23,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26,
    ).forEach { (input, expected) -> Result(expected, solve(input, 14))
        .also { println("\t[$input] : ${it.report()}") }}
}