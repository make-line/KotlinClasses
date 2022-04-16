import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool() : Executor {
    private var threads = mutableListOf<WorkerThread>()
    private var tasks = LinkedBlockingQueue<Runnable>()
    private val obj = Object()

    constructor(count: Int) : this() {
        if (count <= 0) throw IllegalArgumentException("недопустимое клоичество потоков")
        for (i in 1..count) {
            var thread = WorkerThread(tasks, obj)
            thread.start()
            threads.add(thread)
        }
    }


    override fun execute(command: Runnable) {
        synchronized(obj) {
            tasks.add(command)
            obj.notify()
        }


    }

    fun shutdown() {
        threads.forEach() {
            it.stopState = true
            it.interrupt()
        }
    }

}