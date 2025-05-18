package serie2.part1_2

class IntArrayList(capacity: Int) : Iterable <Int> {

    private val data = IntArray(capacity)

    private var head = 0
    private var tail = 0
    private var size = 0
    private var offset = 0



    fun append(x:Int):Boolean {
        if (size == data.size){ //Verifica se a lista está cheia, retorna falso se sim
            return false
        }
        data[tail] = x - offset // Armazena o valor da cauda ajustado pelo offset
        tail = (tail+1) % data.size // Move a cauda de forma circular
        size++                      //Aumenta o tamanho
        return true
    }

    fun get(n:Int):Int?  {
        if (n < 0 || n >= size){ //Verifica se o índice a procurar é válido, retorna null se não
            return null
        }
        val index = (head + n) % data.size // Calcula o índice circular da lista
        return data[index] + offset     // Adiciona o offset de modo a retornar o valor real

    }

    fun addToAll(x:Int)   {
        offset += x //Ajusta o offset global da lista
    }

    fun remove():Boolean {
        if (size == 0){ // Verifica se a lista está vazia
            return false
        }
        head = (head+1) % data.size // Move a cabeça de forma circular
        size --                     // Diminui o tamanho da list
        return true
    }

   override fun iterator(): Iterator<Int> { // Opcional
        TODO()
    }
}