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
    var curr1 = list1.next
    var curr2 = list2.next

    val sentinela1 = list1
    val sentinela2 = list2

    var resultHead: Node<T>? = null
    var resultTail: Node<T>? = null
    var lastInserted: T? = null

    while (curr1 != sentinela1 && curr2 != sentinela2) {
        val comparison = cmp.compare(curr1!!.value, curr2!!.value)

        when {
            comparison == 0 -> {
                if (lastInserted == null || cmp.compare(curr1.value, lastInserted) != 0) {
                    val toAdd = curr1
                    val next1 = curr1.next
                    val next2 = curr2.next

                    removeNodes(curr1)
                    removeNodes(curr2)

                    toAdd.next = null
                    toAdd.previous = null

                    if (resultHead == null) {
                        resultTail = toAdd
                        resultHead = resultTail
                    } else {
                        resultTail!!.next = toAdd
                        toAdd.previous = resultTail
                        resultTail = toAdd
                    }

                    lastInserted = toAdd.value
                    curr1 = next1
                    curr2 = next2
                } else {
                    val next1 = curr1.next
                    val next2 = curr2.next

                    removeNodes(curr1)
                    removeNodes(curr2)

                    curr1 = next1
                    curr2 = next2
                }
            }

            comparison < 0 -> curr1 = curr1.next
            else -> curr2 = curr2.next
        }
    }

    return resultHead
}

fun <T> removeNodes(node: Node<T>){

    node?.previous?.next = node?.next
    node?.next?.previous = node?.previous
}
