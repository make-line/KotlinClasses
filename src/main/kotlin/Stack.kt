private class NodeRev<T>(var data: T) {
    lateinit var prev: NodeRev<T>
    override fun toString(): String {
        return "$data"
    }
}

class Stack<T> {

    private var last: NodeRev<T>? = null

    fun push(obj: T) {
        val node: NodeRev<T> = NodeRev(obj)
        if (last == null) {
            last = node
        } else {
            val prevN = last
            last = node
            last!!.prev = prevN!!
        }
    }

    fun pop(): T {
        val data: T = last!!.data
        last = last!!.prev
        return data
    }

    fun peek(): T {
        return last!!.data
    }

    override fun toString(): String {
        return "Stack(last=$last)"
    }

}