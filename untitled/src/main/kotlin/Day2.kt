fun day2Part1(input: List<String>) = input.sumOf { rpsScore(it) }.toString()

fun rpsScore(round: String) : Int {
    val (opponent, you) = round.split(" ")
    return score(rpsPoint(you), rpsPoint(opponent))
}

/*
you  1  1  2  2  3  3
opp  2  3  1  3  1  2
    -1 -2  1 -1  2  1
+3%3 2  1  1  2  2  1
     L  W  W  L  L  W
 */

fun rpsPoint(move : String) =
when (move) {
    "A", "X" -> 1 // rock
    "B", "Y" -> 2 // paper
    "C", "Z" -> 3 // scissors
    else -> 0
}

private fun score(you: Int, opp: Int) =
    you + when (winner(you, opp)) {
        0 -> 3
        1 -> 6
        else -> 0
    }

private fun winner(youPt: Int, oppPt: Int) = ((youPt - oppPt) + 3) % 3

fun day2Part2(input: List<String>) = input.sumOf { rpsScore2(it) }.toString()

fun rpsScore2(round: String): Int {
    val (opponent, strategy) = round.split(" ")
    val oppPt = rpsPoint(opponent)
    val youPt = strategicPoint(oppPt, targetScore(strategy))
    return score(youPt, oppPt)
}

private val defeat = arrayOf(0, 2, 3, 1)
private val loseTo = arrayOf(0, 3, 1, 2)

fun strategicPoint(oppPt: Int, targetScore: Int) =
when (targetScore) {
    0 -> oppPt
    1 -> defeat[oppPt]
    else -> loseTo[oppPt]
}

private fun targetScore(code: String) =
when (code) {
    "X" -> 2
    "Y" -> 0
    else -> 1
}