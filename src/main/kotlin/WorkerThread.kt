import java.util.concurrent.LinkedBlockingQueue

class WorkerThread(private val tasks: LinkedBlockingQueue<Runnable>, private val obj: Object) : Thread() {
    var stopState = false
    override fun run() {
        while (true) {
            var task: Runnable? = null
            if (stopState) break
            synchronized(obj) {
                if (!tasks.isEmpty()) {
                    task = tasks.remove()
                } else try {
                    obj.wait()
                } catch (e: InterruptedException) {
                    stopState = true
                }
            }
            task?.run()
        }
    }
}