private class Node<T>(var data: T) {
    var next: Node<T>?=null
    override fun toString(): String {
        return "$data"
    }
}

class Queue<T> {
    private var first: Node<T>? = null
    private var last: Node<T>? = null

    fun element(): T {
        if (first == null) {
            throw NoSuchElementException()
        }
        return first!!.data
    }

    fun remove(): T {
        if (first == null) {
            throw NoSuchElementException()
        }
        val data: T = first!!.data
        first = first!!.next
        return data
    }

    fun peek(): T? {
        if (first == null) {
            return null
        }
        return first!!.data
    }

    fun poll(): T? {
        if (first == null) {
            return null
        }
        val data: T = first!!.data
        first = first!!.next
        return data
    }

    fun offer(obj: T): Boolean {
        try {
            val node: Node<T> = Node(obj)
            if (last == null) {
                last = node; first = node; return true
            }
            last!!.next = node
            last = node
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun toString(): String {
        return "Queue(first=$first, last=$last)"
    }
}