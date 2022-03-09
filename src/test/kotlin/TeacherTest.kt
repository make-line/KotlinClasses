import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TeacherTest {
    @Test
    fun `проверка вывода`(){
        val idCardForTeachers = IdCard(200002, 2)
        val teacher=Teacher("Екатерина", "Игоревна", 56, "История", idCardForTeachers)

        assertEquals("Имя: Екатерина\n" +
                "Фамилия: Игоревна\n" +
                "Возраст: 56\n" +
                "Дисциплина: История\n" +
                "Карта: id: 200002, доступ: 2\n"
                ,teacher.getInfo())
    }
    @ParameterizedTest
    @CsvSource("\"Лариса\", \"Петрова\"","\"Marc\", \"Semenov\"")
    fun `проверка вывода c моком`(name: String,lName: String){
        val teacher=mockk<Teacher>()
        every { teacher.getInfo() } returns "teacher $name $lName"
        assertEquals("teacher $name $lName",teacher.getInfo())
    }
    @ParameterizedTest
    @CsvSource("\"Лариса\", \"Петрова\",\"Marc\", \"Semenov\"","\"Олег\", \"Петров\",\"Marc\", \"Lo\"")
    fun `проверка вывода школы`(name: String,lName: String,nameS: String,lNameS: String){
        var teacher=mockk<Teacher>()
        var student=mockk<Student>()
        every { teacher.firstName } returns  name
        every { teacher.lastName } returns lName
        every { student.firstName } returns nameS
        every { student.lastName } returns lNameS
        val school=School(arrayListOf(student), arrayListOf(teacher), 1973, "Ул. Ленина, дом 36")
        assertAll(
            { assertEquals("Ученики: [$nameS $lNameS]\n" +
                    "Учителя: [$name $lName]\n" +
                    "Номер школы: 1973\n" +
                    "Адресс школы: Ул. Ленина, дом 36\n",school.toString())},
            { verify(exactly = 1){school.toString()} }
        )
    }
}