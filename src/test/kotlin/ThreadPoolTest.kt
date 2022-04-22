import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows


class ThreadPoolTest : Object() {
    private val threadPool = ThreadPool(5)

    @Test
    fun threadPoolWorkTest() {

        assertDoesNotThrow {
            synchronized(this) {
                for (i in 0..9) {
                    threadPool.execute {
                        println("задача-$i")
                    }
                }
                this.wait(1000)
            }
            threadPool.shutdown()
        }
    }

    @Test
    fun testWhenThreadsQuantityIsUnavailable() {
        val result = assertThrows<IllegalArgumentException> {
            ThreadPool(0)
        }
        assertEquals("недопустимое клоичество потоков", result.message)
    }
}