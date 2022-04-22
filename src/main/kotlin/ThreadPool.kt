import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool() : Executor {
    private var threads = mutableListOf<WorkerThread>()
    private var tasks = LinkedBlockingQueue<Runnable>()

    constructor(count: Int) : this() {
        if (count <= 0) throw IllegalArgumentException("недопустимое клоичество потоков")
        repeat(count){
            val thread = WorkerThread(tasks)
            thread.start()
            threads.add(thread)
        }
    }


    override fun execute(command: Runnable) {
        synchronized(tasks) {
            tasks.add(command)
            (tasks as Object).notify()
        }


    }

    fun shutdown() {
        threads.forEach {
            it.setStopState(true)
            it.interrupt() }
    }

    private inner class WorkerThread(private val tasks: LinkedBlockingQueue<Runnable>) : Thread() {
        @Volatile
        private var stopState= false
        fun setStopState (bool: Boolean){ this.stopState=bool}
        override fun run() {
            while (true) {
                var task: Runnable? = null
                if (stopState) break
                synchronized(tasks) {
                    if (!tasks.isEmpty()) {
                        task = tasks.remove()
                    } else {
                        try {
                            (tasks as Object).wait()
                        } catch (e: InterruptedException) {
                            currentThread().interrupt()
                        }
                    }

                }
                task?.run()
            }
        }
    }

}