
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll


class QueueTest {
    @Test
    fun `peek если очередь не пуста`(){
        val queue: Queue<String> = Queue()
        queue.offer("abc")
        queue.offer("bda")
        queue.offer("fge")
        queue.offer("dgf")
        assertEquals("abc",queue.peek())
    }
    @Test
    fun `peek если очередь пуста`(){
        val queue: Queue<String> = Queue()

        assertEquals(null,queue.peek())
    }
    @Test
    fun `peek если в очереди 1 элеменет`(){
        val queue: Queue<String> = Queue()
        queue.offer("abc")
        assertEquals("abc",queue.peek())


    }

    @Test
    fun `element если очередь не пуста`(){
        val queue: Queue<String> = Queue()
        queue.offer("abc")
        queue.offer("bda")
        queue.offer("fge")
        queue.offer("dgf")
        assertEquals("abc",queue.element())
    }
    @Test
    fun `element если очередь пуста`(){
        val queue: Queue<String> = Queue()

        Assertions.assertThrows(NoSuchElementException::class.java) {queue.element()}
    }
    @Test
    fun `element если в очереди 1 элеменет`(){
        val queue: Queue<String> = Queue()
        queue.offer("abc")
        assertEquals("abc",queue.element())
    }

    @Test
    fun `remove если очередь 2+`(){
        val queue: Queue<String> = Queue()
        queue.offer("abc")
        queue.offer("bda")
        queue.offer("fge")
        queue.offer("dgf")
        assertAll(
            {
                assertEquals("abc",queue.remove())
                assertEquals("bda",queue.element())
            }
        )
    }
    @Test
    fun `remove если очередь пуста`(){
        val queue: Queue<String> = Queue()
        Assertions.assertThrows(NoSuchElementException::class.java) {queue.remove()}
    }
    @Test
    fun `remove если в очереди 1 элеменет`(){
        val queue: Queue<String> = Queue()
        queue.offer("abc")
        assertAll(
            {
            assertEquals("abc",queue.remove())
            assertEquals(null,queue.peek())
        }
        )
    }

    @Test
    fun `poll если очередь 2+`(){
        val queue: Queue<String> = Queue()
        queue.offer("abc")
        queue.offer("bda")
        queue.offer("fge")
        queue.offer("dgf")
        assertAll(
            {
                assertEquals("abc",queue.poll())
                assertEquals("bda",queue.element())
            }
        )
    }
    @Test
    fun `poll если очередь пуста`(){
        val queue: Queue<String> = Queue()
        assertEquals(null,queue.poll())
    }
    @Test
    fun `poll если в очереди 1 элеменет`(){
        val queue: Queue<String> = Queue()
        queue.offer("abc")
        assertAll(
            {
                assertEquals("abc",queue.poll())
                assertEquals(null,queue.peek())
            }
        )
    }
    @Test
    fun offerTest() {
        val queue: Queue<String> = Queue()
        assertEquals(true,queue.offer("abc"))
    }


}