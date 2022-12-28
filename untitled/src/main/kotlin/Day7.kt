class Day7  (
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution {

    override val name: String get() = "Day 7 ($fileName)"
    private val root = parse(InputReader(fileName).lines())

    override fun part1() = Result(expected1, sumOfSmallDirectories())

    private fun sumOfSmallDirectories(): Int {
        val smallDirs: MutableList<Int> = mutableListOf()

        root.walkDirectories { if (it.size() <= 100_000) smallDirs.add(it.size()) }

        return smallDirs.sum()
    }

    override fun part2() = Result(expected2, smallestDirectoryToDelete())

    private fun smallestDirectoryToDelete(): Int {
        val eligibleSizes: MutableList<Int> = mutableListOf()
        val spaceNeeded = spaceNeeded(root.size())

        root.walkDirectories { if (it.size() >= spaceNeeded) eligibleSizes.add(it.size()) }

        return eligibleSizes.min()
    }

    private fun spaceNeeded(used: Int) = 30_000_000 - (70_000_000 - used)

    private fun parse(input: List<String>): FileAoC7 {
        val root = FileAoC7("/")
        var currentDir = root
        val dirStack: Stack<FileAoC7> = Stack()

        fun dirName(line: String) = line.split(" ")[1]

        fun toFile(line: String) = line.split(" ").let { FileAoC7(it[1], it[0].toInt()) }

        fun toDir(line: String) = FileAoC7(dirName(line))

        fun upDir() = if (dirStack.isNotEmpty()) dirStack.pop() else dirStack.push(root).let { root }

        fun chdir(line: String) = dirStack.push(currentDir).let {
            currentDir.contents.first { it.name == dirName(line.substring(2)) }
        }

        input.forEach { line ->
            when {
                line.startsWith("$ cd /") -> dirStack.push(root)
                line.startsWith("$ cd ..") -> currentDir = upDir()
                line.startsWith("$ cd ") -> currentDir = chdir(line)
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

    fun walkDirectories(action: (FileAoC7) -> Unit) {
        action.invoke(this)
        contents.filter { it.isDirectory }.forEach { it.walkDirectories(action) }
    }
}
