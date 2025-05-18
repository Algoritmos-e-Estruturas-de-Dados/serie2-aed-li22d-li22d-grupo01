package serie2.problema

fun main() {
    val startTime = System.currentTimeMillis() // Começa a contar o tempo

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
    println("Ficheiros carregados.")

    println("Aplicação pronta. Comandos disponíveis:")
    println("union <ficheiro>")
    println("intersection <ficheiro>")
    println("difference <ficheiro>")
    println("exit")

    while (true) {
        print("> ")
        val input = readlnOrNull()?.trim() ?: break
        val parts = input.split(" ")

        when (parts[0]) {
            "union" -> {
                if (parts.size == 2) {
                    engine.union(parts[1])
                    println("Ficheiro ${parts[1]} criado.")
                } else println("Uso: union <ficheiro>")
            }

            "intersection" -> {
                if (parts.size == 2) {
                    engine.intersection(parts[1])
                    println("Ficheiro ${parts[1]} criado.")
                } else println("Uso: intersection <ficheiro>")
            }

            "difference" -> {
                if (parts.size == 2) {
                    engine.difference(parts[1])
                    println("Ficheiro ${parts[1]} criado.")
                } else println("Uso: difference <ficheiro>")
            }

            "exit" -> {
                val endTime = System.currentTimeMillis()
                val elapsed = endTime - startTime
                println("A sair...")
                println("Tempo total de execução: ${elapsed} ms")
                return
            }

            else -> println("Comando não reconhecido.")
        }
    }
}
