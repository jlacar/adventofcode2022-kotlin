class Day7  (
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution {

    override val name: String get() = "Day 7 ($fileName)"
    private val root = parse(InputReader(fileName).lines())

    override fun part1() = Result(expected1, smallDirectories().sumOf { it.size() })

    private fun smallDirectories() = findDirs { it.size() <= 100_000 }

    override fun part2() = Result(expected2, smallestDirectoryToDelete().size())

    private fun smallestDirectoryToDelete() = spaceNeeded().let { minSize ->
        findDirs { it.size() >= minSize }.minBy { it.size() } }

    private fun spaceNeeded() = 30_000_000 - (70_000_000 - root.size())

    private fun findDirs(predicate: (FileAoC7) -> Boolean): List<FileAoC7> = mutableListOf<FileAoC7>()
        .also { matches -> root.walkDirectories { if (predicate.invoke(it)) matches.add(it) } }

    private fun parse(input: List<String>): FileAoC7 {
        val root = FileAoC7("/")
        val dirStack = Stack<FileAoC7>()

        fun dirName(line: String) = line.split(" ")[1]
        fun toDir(line: String) = FileAoC7(dirName(line))
        fun toFile(line: String) = line.split(" ").let { FileAoC7(it[1], it[0].toInt()) }
        fun currentDir() = dirStack.peek()!!.contents
        fun chdir(dir: String) = currentDir().first { it.name == dir }.also { dirStack.push(it) }

        input.forEach { line ->
            when {
                line.startsWith("$ cd /") -> dirStack.push(root)
                line.startsWith("$ cd ..") -> dirStack.pop()
                line.startsWith("$ cd ") -> chdir(dirName(line.substring(2)))
                line.startsWith("dir ") -> currentDir().add(toDir(line))
                line.first().isDigit() -> currentDir().add(toFile(line))
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
        if (!isDirectory) return
        action.invoke(this)
        contents.filter { it.isDirectory }.forEach { it.walkDirectories(action) }
    }
}