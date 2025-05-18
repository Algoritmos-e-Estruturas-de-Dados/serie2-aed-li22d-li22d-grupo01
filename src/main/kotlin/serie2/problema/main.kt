package serie2.problema

fun main() {
    val totalStart = System.currentTimeMillis() // Tempo total começa

    println("Escolhe a implementação:")
    println("1 - Usar HashMap da Kotlin")
    println("2 - Usar HashMap da pergunta I.4")
    print("> ")

    val command = readlnOrNull()?.trim()

    val engine: PointsCollectionEngine = when (command) {
        "1" -> ProcessPointsCollection1
        "2" -> ProcessPointsCollection2
        else -> {
            println("Escolha inválida.")
            return
        }
    }

    print("Ficheiro 1: ")
    val file1 = readlnOrNull()?.trim()
    if (file1.isNullOrBlank()) {
        println("Nome de ficheiro inválido.")
        return
    }

    print("Ficheiro 2: ")
    val file2 = readlnOrNull()?.trim()
    if (file2.isNullOrBlank()) {
        println("Nome de ficheiro inválido.")
        return
    }

    println("A carregar ficheiros $file1 e $file2...")
    engine.load(file1, file2)
    println("Ficheiros carregados com sucesso.")

    println("A executar operações...")

    // Union
    val startUnion = System.currentTimeMillis()
    engine.union("union.out")
    val endUnion = System.currentTimeMillis()
    println("union.out criado em ${endUnion - startUnion} ms")

    // Intersection
    val startIntersection = System.currentTimeMillis()
    engine.intersection("intersection.out")
    val endIntersection = System.currentTimeMillis()
    println("intersection.out criado em ${endIntersection - startIntersection} ms")

    // Difference
    val startDifference = System.currentTimeMillis()
    engine.difference("difference.out")
    val endDifference = System.currentTimeMillis()
    println("difference.out criado em ${endDifference - startDifference} ms")

    val totalEnd = System.currentTimeMillis()
    println("Tempo total de execução: ${totalEnd - totalStart} ms")
}
