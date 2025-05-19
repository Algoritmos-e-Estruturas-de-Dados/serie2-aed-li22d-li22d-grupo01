package serie2.part1_2

class IntArrayList(private val capacity: Int) : Iterable <Int> {

    private val data = IntArray(capacity)

    private var primeiro = 0

    private var ultimo = 0

    private var tamanho = 0

    private var offset = 0

    fun append(x:Int):Boolean {
        if(tamanho == capacity ) return false
        data[ultimo] = x - offset                    // Armazenamos o valor compensado
        ultimo = (ultimo + 1) % capacity               // Move o tail circularmente
        tamanho++                                     // Aumenta o tamanho
        return true

    }

    fun get(n:Int):Int?  {
        if (n < 0 || n >= tamanho) return null          // Índice inválido
        val indice = (primeiro + n) % capacity            // Cálculo do índice real no array circular
        return data[indice] + offset
    }

    fun addToAll(x:Int)   {
        offset += x
    }

    fun remove():Boolean {
        if (tamanho == 0) return false             // Lista vazia → nada a remover
        primeiro = (primeiro + 1) % capacity            // Avança o início da lista
        tamanho--                                  // Diminui o tamanho
        return true
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var index = 0

            override fun hasNext(): Boolean {
                return  index < tamanho

            }

            override fun next(): Int {
                val realIndex = (primeiro + index) % capacity
                index++
                return data[realIndex] + offset
            }
        }
    }
}
