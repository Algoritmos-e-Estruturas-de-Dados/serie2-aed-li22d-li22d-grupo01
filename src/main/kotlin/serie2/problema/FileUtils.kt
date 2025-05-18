package serie2.problema

import java.io.FileReader
import java.io.BufferedReader
import java.io.PrintWriter


fun reader(fileName: String): BufferedReader{
    return BufferedReader(FileReader(fileName))
}


fun writer(fileName: String): PrintWriter {
    return PrintWriter(fileName)
}


enum class Source {FILE1, FILE2, BOTH}

interface PointsCollectionEngine {
    fun load(file1: String, file2: String)
    fun union(output: String)
    fun intersection(output: String)
    fun difference(output: String)
}
