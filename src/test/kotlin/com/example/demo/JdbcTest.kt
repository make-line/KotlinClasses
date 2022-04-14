package com.example.demo

import com.example.demo.model.School
import com.example.demo.repository.JdbcSchoolRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

@JdbcTest
class JdbcTest {
    @Autowired
    private lateinit var ds: DataSource

    private lateinit var jdbcSchoolRepository: JdbcSchoolRepository

    @BeforeEach
    fun setUp() {
        jdbcSchoolRepository = JdbcSchoolRepository(JdbcTemplate(ds))
    }

    @Test
    fun getSchoolTest() {
        var result = jdbcSchoolRepository.getSchoolById(1)
        assertEquals(School(1, "Черкизовская 2", 4, 73), result)
    }

    @Test
    fun addSchoolTest() {
        var result = jdbcSchoolRepository.saveSchool(School(4, "Черкизовская 2", 4, 73))
        assertEquals(School(4, "Черкизовская 2", 4, 73), result)
    }

    @Test
    fun getSchoolsByCountTest() {
        var result = jdbcSchoolRepository.getSchoolsWhereCountOfStudentsBigger(400)
        assertEquals(listOf<School>(School(2, "Черкизовская 5", 50, 730), School(3, "Черкизовская 8", 34, 800)), result)
    }
}