import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StackTest {
    @Test
    fun peekTest(){
        val stack: Stack<Int> = Stack()
        stack.push(1)
        stack.push(2)
        stack.push(3)
        Assertions.assertEquals(3, stack.peek())
    }
    @Test
    fun popTest(){
        val stack: Stack<Int> = Stack()
        stack.push(1)
        stack.push(2)
        stack.push(3)
        Assertions.assertEquals(3, stack.pop())
        Assertions.assertEquals(2, stack.peek())
    }
}