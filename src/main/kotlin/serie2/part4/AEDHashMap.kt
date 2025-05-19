package serie2.part4

class AEDHashMap<K, V> (initialCapacity: Int = 16, val loadFactor: Float = 0.75f): AEDMutableMap<K, V> {
    class HashNode<K, V>(
        override val key: K, override var value: V,
        var next: HashNode<K, V>? = null
    ) : AEDMutableMap.MutableEntry<K, V> {
        var hc = key.hashCode()
        override fun setValue(newValue: V): V {
            val oldValue = value
            value = newValue
            return oldValue
        }

    }

    override var size: Int = 0
    override var capacity: Int = initialCapacity
    private var table: Array<HashNode<K, V>?> = arrayOfNulls(initialCapacity)

    inner class MapIterator : Iterator<AEDMutableMap.MutableEntry<K, V>> {
        var pos = 0
        var currNode: HashNode<K, V>? = null
        var currNodeIter: HashNode<K, V>? = null
        override fun hasNext(): Boolean {
            if (currNode != null) return true;
            while (pos < table.size) {
                if (currNodeIter == null) {
                    currNodeIter = table[pos++]
                } else {
                    currNode = currNodeIter
                    currNodeIter = currNodeIter?.next
                    return true
                }
            }
            return false
        }

        override fun next(): AEDMutableMap.MutableEntry<K, V> {
            if (!hasNext()) throw NoSuchElementException()
            val toReturn = currNode
            currNode = null
            return toReturn!!
        }
    }

    override fun iterator(): Iterator<AEDMutableMap.MutableEntry<K, V>> {
        return MapIterator()
    }

    private fun hash(HashCode: Int): Int {
        return HashCode.and(0x7fffffff) % table.size
    }

    override fun get(key: K): V? {
        val idx = hash(key.hashCode())
        var currNode = table[idx]
        while (currNode != null) {
            if (currNode.key == key) {
                return currNode.value
            }
            currNode = currNode.next
        }
        return null
    }

    private fun expand() {
        val oldTable = table
        capacity *= 2
        table = arrayOfNulls(capacity)

        for (node in oldTable) {
            var currNode = node
            while (currNode != null) {
                val nextNode = currNode.next
                val idx = hash(currNode.hc)
                currNode.next = table[idx]
                table[idx] = currNode
                currNode = nextNode
            }
        }
    }


    override fun put(key: K, value: V): V? {
        val index = (key.hashCode() and 0x7fffffff) % capacity
        var node = table[index]

        while (node != null) {
            if (node.key == key) {
                val oldValue = node.value
                node.value = value
                return oldValue
            }
            node = node.next
        }

        val newNode = HashNode(key, value)
        newNode.next = table[index]
        table[index] = newNode
        size++

        if (size >= (capacity * loadFactor).toInt()) {
            expand()
        }

        return null
    }
}
