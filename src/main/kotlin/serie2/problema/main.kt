package serie2.problema

import kotlin.time.*
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalTime::class)
fun main() {
    println("Aplicação ProcessPointsCollections iniciada.")
    println("Escolhe a implementação? (1 - HashMap da Kotlin | 2 - HashMap da pergunta I.4)")

    val engine: PointsCollectionEngine = when (readln().toIntOrNull()) {
        1 -> ProcessPointsCollection1
        2 -> ProcessPointsCollection2
        else -> {
            println("Erro: Implementação inválida.")
            return
        }
    }

    var loaded = false

    println(
        """
        Comandos disponíveis:
        - load <ficheiro1.co> <ficheiro2.co>   → Carrega os ficheiros de pontos
        - union <output.co>                    → Guarda a união dos pontos
        - intersection <output.co>             → Guarda a interseção dos pontos
        - difference <output.co>               → Guarda a diferença dos pontos (apenas em ficheiro1)
        - exit                                 → Termina a aplicação
        """.trimIndent()
    )

    while (true) {
        print("> ")
        val input = readLine()?.trim() ?: continue
        val parts = input.split(" ")

        when (parts[0].lowercase()) {
            "load" -> {
                if (parts.size != 3) {
                    println("Uso: load ficheiro1.co ficheiro2.co")
                } else {
                    val time = measureTime {
                        engine.load(parts[1], parts[2])
                    }
                    loaded = true
                    println("Ficheiros carregados com sucesso (${time.inWholeMilliseconds} ms).")
                }
            }

            "union" -> {
                if (!loaded) println("Erro: ficheiros não carregados. Use o comando load primeiro.")
                else if (parts.size != 2) println("Uso: union output.co")
                else {
                    val time = measureTime {
                        engine.union(parts[1])
                    }
                    println("União guardada em ${parts[1]} (${time.inWholeMilliseconds} ms).")
                }
            }

            "intersection" -> {
                if (!loaded) println("Erro: ficheiros não carregados. Use o comando load primeiro.")
                else if (parts.size != 2) println("Uso: intersection output.co")
                else {
                    val time = measureTime {
                        engine.intersection(parts[1])
                    }
                    println("Interseção guardada em ${parts[1]} (${time.inWholeMilliseconds} ms).")
                }
            }

            "difference" -> {
                if (!loaded) println("Erro: ficheiros não carregados. Use o comando load primeiro.")
                else if (parts.size != 2) println("Uso: difference output.co")
                else {
                    val time = measureTime {
                        engine.difference(parts[1])
                    }
                    println("Diferença guardada em ${parts[1]} (${time.inWholeMilliseconds} ms).")
                }
            }

            "exit" -> {
                println("A terminar aplicação.")
                return
            }

            else -> {
                println("Comando desconhecido: ${parts[0]}")
                println(
                    """
                    Comandos disponíveis:
                    - load <ficheiro1.co> <ficheiro2.co>
                    - union <output.co>
                    - intersection <output.co>
                    - difference <output.co>
                    - exit
                    """.trimIndent()
                )
            }
        }
    }
}
