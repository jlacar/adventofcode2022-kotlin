
fun day6Part1(input: String) = markerOffset(input, 4).toString()

fun markerOffset(dataStream: String, size: Int) =
    dataStream.windowed(size, 1).indexOfFirst { isMarker(it) } + size

fun isMarker(packet: String) = packet.toSet().size == packet.length

fun day6Part2(input: String) = markerOffset(input, 14).toString()

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

    for (s in testData1) {
        println(day6Part1(s))
    }
    for (s in testData2) {
        println(day6Part2(s))
    }
}