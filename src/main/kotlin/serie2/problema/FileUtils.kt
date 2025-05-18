package serie2.problema

import java.io.FileReader
import java.io.BufferedReader
import java.io.PrintWriter

fun reader(fileName: String): BufferedReader{
    return BufferedReader(FileReader(fileName))
}

fun writer(fileName: String): PrintWriter{
    return PrintWriter(fileName)
}

enum class Source {FILE1, FILE2, BOTH}

interface PointsCollectionEngine {
    fun load(file1: String, file2: String)
    fun union(output: String)
    fun intersection(output: String)
    fun difference(output: String)
}



/*
fun readFile(fileName: String): List<Point> =
    File(fileName).readLines()
        .filter { it.startsWith("v ") }
        .mapNotNull {
            val parts = it.split(" ")
            if (parts.size == 4) {
                val id = parts[1]
                val x = parts[2].toIntOrNull()
                val y = parts[3].toIntOrNull()
                if (x != null && y != null) Point(id, x, y) else null
            } else null
        }

fun writeFile(points: Collection<Point>, fileName: String) {
    File(fileName).printWriter().use { writer ->
        for (point in points) {
            writer.println(" ${point.id} ${point.x} ${point.y}")
        }
    }
}
 */