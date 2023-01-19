class Day6(private val fileName: String) : AocSolution {
    override val description get() = "Day 6 - Tuning Trouble ($fileName)"

    private val input = InputReader(fileName).text
    override fun part1() = solve(input, 4)
    override fun part2() = solve(input, 14)
}

private fun solve(dataStream: String, packetSize: Int) =
    dataStream.windowed(packetSize, 1).indexOfFirst { isMarker(it) } + packetSize

private fun isMarker(packet: String) = packet.toSet().size == packet.length

fun test(packetSize: Int, input: Map<String, Int>) {
    input.map { (input, expected) ->
        Pair(expected, solve(input, packetSize))
    }.map { (expected, actual) ->
        if (expected == actual) "Ok: $actual"
        else "Fail: expected [$expected] but got [$actual]"
    }.forEach(::println)
}

fun main() {
    test(4, mapOf(
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
        "nppdvjthqldpwncqszvftbrmjlhg" to 6,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11,
    ))

    test(14, mapOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
        "nppdvjthqldpwncqszvftbrmjlhg" to 23,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26,
    ))

    Day6("Day6.txt") shouldHave {
        part1of(1093)
        part2of(3534)
    }

    Day6("Day6-alt.txt") shouldHave {
        part1of(1155)
        part2of(2789)
    }

//  can we do this?
//
//    Day6("Day6.txt") shouldHave {
//        resultOf("Ok") forIts { testPart1() }
//        resultOf("Ok") forIts { testPart2() }
//
//        resultOf(1093) forIts { part1() }
//        resultOf(3534) forIts { part2() }
//    }

}