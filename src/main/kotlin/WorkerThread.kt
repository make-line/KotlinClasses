import java.util.concurrent.LinkedBlockingQueue

class WorkerThread(private val tasks:LinkedBlockingQueue<Runnable>, private val obj:Object): Thread() {
    var stopState=false
    override fun run() {
        var task: Runnable? =null
            while (true) {
                if(stopState) break
                synchronized(obj) {
                    if (!tasks.isEmpty()) {
                        task=tasks.remove()
                    } else try {
                        obj.wait()
                    } catch (e: InterruptedException) {
                        stopState = true
                    }
                }
                task?.run()
                task=null
            }
        }
    }