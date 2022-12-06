fun day6Part1(input: String) = markerOffset(input, 4).toString()

fun day6Part2(input: String) = markerOffset(input, 14).toString()

fun markerOffset(dataStream: String, size: Int) =
    dataStream.windowed(size, 1).indexOfFirst { isMarker(it) } + size

fun isMarker(packet: String) = packet.toSet().size == packet.length


fun main() {
    val testData1: List<String> = listOf(
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",
    )

    val testData2 = listOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",
    )

    println("Part 1 test")
    testData1.forEach { day6Part1(it).also(::println) }

    println("Part 2 test")
    testData2.forEach { day6Part2(it).also(::println) }
}