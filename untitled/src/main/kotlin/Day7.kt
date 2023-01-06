class Day7  (
    private val fileName: String,
    private val expected1: Int,
    private val expected2: Int) : Solution {

    override val day get() = 7
    override val source get() = "$fileName"

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

        fun dirName(line: String) = line.split(" ")[2]
        fun directoryFrom(line: String) = FileAoC7(line.split(" ")[1])
        fun fileFrom(line: String) = line.split(" ").let { (bytes, name) -> FileAoC7(name, bytes.toInt()) }
        fun currentDir() = dirStack.peek()!!.contents
        fun chdir(dir: String) = currentDir().first { it.name == dir }.also { dirStack.push(it) }

        input.forEach { line ->
            when {
                line.startsWith("$ cd /") -> dirStack.push(root)
                line.startsWith("$ cd ..") -> dirStack.pop()
                line.startsWith("$ cd ") -> chdir(dirName(line))
                line.startsWith("dir ") -> currentDir().add(directoryFrom(line))
                line.first().isDigit() -> currentDir().add(fileFrom(line))
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
        if (isDirectory) action.invoke(this).also { contents.forEach { it.walkDirectories(action) } }
    }
}