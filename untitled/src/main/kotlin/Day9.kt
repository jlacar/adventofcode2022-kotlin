//import Day9.Direction.*

class Day9(
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution
{
//    override val day get() = 9
//    override val source get() = "$fileName"
//
    override fun part1() = PendingResult // (expected1, distinctTailPositions().count())
//
//    enum class Direction { R, L, U, D, NONE }
//
//    data class Position(val x: Int, val y: Int) {
//
//        operator fun plus(other: Position) = Position(this.x + other.x, this.y + other.y)
//
//        fun step(dir: Direction) = when (dir) {
//            R -> this.copy(x = this.x + 1)
//            L -> this.copy(x = this.x - 1)
//            U -> this.copy(y = this.y + 1)
//            D -> this.copy(y = this.y - 1)
//            else -> this.copy()
//        }
//
//        fun offsetFrom(other: Position) = Position(other.x - this.x, other.y - this.y)
//
//        override fun toString() = "($x, $y)"
//    }
//
//    private val moves = parse(InputReader(fileName).lines())
//
//    class Knot(private val tail: Knot?) {
//        val visited = mutableListOf(Position(0, 0))
//
//        fun move(direction: Direction, steps: Int = 1) =
//            (1..steps).map { _ ->
//                visited.add(visited.last().move(direction))
//                tail?.follow(visited.last(), direction)
//            }
//
//        private fun follow(head: Position, direction: Direction) =
//            (if (tooFar(head)) reposition(direction, head) else visited.last()).let { visited.add(it) }
//
//        private fun reposition(direction: Direction, head: Position): Position {
//            visited.last().move(direction).move(lateral(head, direction))
//        }
//
//        private fun lateral(head: Position, direction: Direction): Direction {
//
//        }
//
//        private fun tooFar(head: Position) = with (visited.last()) {
//            abs(head.x - x) > 1 || abs(head.y - y) > 1
//        }
//    }
//
//    class Rope {
//        var head = Position(0, 0)
//        var tail = Position(0, 0)
//
//        fun tailPositions(steps: Pair<Direction, Int>) = steps.let { (direction, count) ->
//            (1..count).map { _ ->
//                head = head.move(direction)
//                tail = followHead(direction)
//                tail
//            }
//        }
//
//        private fun followHead(direction: Direction) = if (tailShouldMove())
//            tail.move(direction).move(diagonal(direction)) else tail
//
//        private fun tailShouldMove() = abs(head.x - tail.x) > 1 || abs(head.y - tail.y) > 1
//
//        private fun diagonal(direction: Direction) =
//            when (direction) {
//                R, L -> alignTailHorizontally()
//                U, D -> alignTailVertically()
//                else -> NONE
//            }
//
//        private fun alignTailVertically() = when {
//            tail.x < head.x -> R
//            tail.x > head.x -> L
//            else -> NONE
//        }
//
//        private fun alignTailHorizontally() = when {
//            tail.y < head.y -> U
//            tail.y > head.y -> D
//            else -> NONE
//        }
//    }
//
//    private val rope = Rope()
//
//    private fun distinctTailPositions() = moves.map { steps -> rope.tailPositions(steps) }.flatten().distinct()
//
    override fun part2() = PendingResult
//
//    private fun parse(lines: List<String>) = lines.map { move ->
//        move.split(" ").let { (direction, steps) ->
//            Pair(valueOf(direction), steps.toInt())
//        }
//    }
}

fun main() {
//    Solution.report(
//        Day9("Day9-sample.txt", 13, 0),
//        Day9("Day9.txt", 6563, 0),
//    )
}