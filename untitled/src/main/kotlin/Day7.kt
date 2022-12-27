
class Day7  (
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution {

    override val name: String get() = "Day 7 ($fileName)"
    private val input = InputReader(fileName).lines()

    override fun part1() = Result(expected1, sumOf100kDirs(input))

    override fun part2() = PendingResult

    private fun sumOf100kDirs(input: List<String>): Int {
        val root = parse(input)
        return root.walk(100_000)
    }

    private fun parse(input: List<String>): FileAoC7 {
        val root = FileAoC7("/")
        var currentDir = root
        val dirStack: Stack<FileAoC7> = Stack()

        fun dirName(line: String) = line.split(" ")[1]

        fun toFile(line: String) = line.split(" ").let { FileAoC7(it[1], it[0].toInt()) }

        fun toDir(line: String) = FileAoC7(dirName(line))

        fun upDir() = if (dirStack.isNotEmpty()) dirStack.pop() else dirStack.push(root).let { root }

        fun chdir(line: String) = dirStack.push(currentDir).let { _ ->
            currentDir.contents.first { it.name == dirName(line.substring(2)) }
        }

        input.forEach { line ->
            when {
                line.startsWith("$ cd /") -> dirStack.push(root)
                line.startsWith("$ cd ..") -> currentDir = upDir()
                line.startsWith("$ cd ") -> currentDir = chdir(line)
                line.startsWith("$ ls") -> { /* no-op */ }
                line.startsWith("dir ") -> currentDir.contents.add(toDir(line.trim()))
                line.first().isDigit() -> currentDir.contents.add(toFile(line.trim()))
            }
        }

        return root
    }
}


data class FileAoC7 (val name: String, val bytes: Int, val isDirectory: Boolean = false) {
    constructor(name: String) : this(name,0, true) {}

    val contents: MutableList<FileAoC7> = mutableListOf()
    fun size(): Int = if (isDirectory) contents.sumOf { it.size() } else bytes

    fun walk(maxSize: Int): Int {
        return (if (size() <= maxSize) size() else 0) + contents.sumOf { if (it.isDirectory) it.walk(maxSize) else 0 }
    }
}
