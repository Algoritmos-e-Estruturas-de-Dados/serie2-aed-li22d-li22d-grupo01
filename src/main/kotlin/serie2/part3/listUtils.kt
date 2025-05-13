package serie2.part3

class Node<T> (
    var value: T = Any() as T,
    var next: Node<T>? = null,
    var previous: Node<T>? = null) {
}

fun splitEvensAndOdds(list:Node<Int>){

    var current = list.next // Primeiro nó depois da sentinela

    while (current != list){ // Percorre a lista até encontrar a sentinela

        var nextNode = current!!.next // Armazena o próximo nó

        if (current.value % 2 == 0){ // Verifica se o value do nó atual é par

            //Remove o nó atual e refaz as ligações
            current.previous!!.next = current.next
            current.next!!.previous = current.previous

            //Insere o nó logo depois da sentinela
            current.next = list.next
            current.previous = list
            list.next!!.previous = current
            list.next = current
        }
        current = nextNode
    }
}

fun <T> intersection(list1: Node<T>, list2: Node<T>, cmp: Comparator<T>): Node<T>? {
    TODO()
}