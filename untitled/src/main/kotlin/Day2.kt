class Day2(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution {

    override val day get() = 2
    override val source get() = "$fileName"

    private val input = InputReader(fileName).lines()
    override fun part1() = Result(expected1, input.sumOf { rpsScore(it) })
    override fun part2() = Result(expected2, input.sumOf { rpsStrategicScore(it) })
}

fun rpsScore(round: String): Int {
    val (opponentMove, yourMove) = round.split(" ")
    return roundScore(rpsPoint(yourMove), rpsPoint(opponentMove))
}

fun rpsStrategicScore(round: String): Int {
    val (opponentMove, strategy) = round.split(" ")
    val oppPt = rpsPoint(opponentMove)
    val youPt = strategicPoint(oppPt, resultWanted(strategy))
    return roundScore(youPt, oppPt)
}

fun rpsPoint(move: String) = pointsFor[move] ?: 0
private val pointsFor = mapOf("A" to 1, "X" to 1, "B" to 2, "Y" to 2, "C" to 3, "Z" to 3)

private fun roundScore(you: Int, opp: Int) = you + score[result(you, opp)]
private val score = arrayOf(3, 6, 0)

/*
you   1  1  2  2  3  3     1 - rock
opp   2  3  1  3  1  2     2 - paper
dif  -1 -2  1 -1  2  1     3 - scissors
+3%3  2  1  1  2  2  1
      L  W  W  L  L  W
 */
private fun result(you: Int, opp: Int) = (you - opp + 3) % 3

private const val DRAW = 0
private const val WIN = 1
private const val LOSE = 2

private val defeat = arrayOf(0, 2, 3, 1)
private val loseTo = arrayOf(0, 3, 1, 2)

fun strategicPoint(opponent: Int, resultWanted: Int) =
    when (resultWanted) {
        DRAW -> opponent
        WIN -> defeat[opponent]
        else -> loseTo[opponent]
    }

private fun resultWanted(strategy: String) =
    when (strategy) {
        "X" -> LOSE
        "Y" -> DRAW
        else -> WIN
    }
