package com.example.demo

import com.example.demo.model.School
import com.example.demo.repository.JdbcSchoolRepository
import com.example.demo.repository.JpaSchoolRepository
import com.example.demo.repository.SchoolJpaRepositoryInterface
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@DataJpaTest
class JpaTest {
    @Autowired
    private lateinit var jpaRepositoryInterface: SchoolJpaRepositoryInterface

    private lateinit var jpaSchoolRepository: JpaSchoolRepository
    @BeforeEach
    fun setUp() {
        jpaSchoolRepository = JpaSchoolRepository(jpaRepositoryInterface)
    }
    @Test
    fun getSchoolTest()
    {
        var result=jpaSchoolRepository.getSchoolById(1)
        Assertions.assertEquals(School(1, "Черкизовская 2", 4, 73), result)
    }

    @Test
    fun addSchoolTest()
    {
        var result=jpaSchoolRepository.saveSchool(School(4, "Черкизовская 2", 4, 73))
        Assertions.assertEquals(School(4, "Черкизовская 2", 4, 73), result)
    }

    @Test
    fun getSchoolsByCountTest()
    {
        var result=jpaSchoolRepository.getSchoolsWhereCountOfStudentsBigger(400)
        Assertions.assertEquals(
            listOf<School>(
                School(2, "Черкизовская 5", 50, 730),
                School(3, "Черкизовская 8", 34, 800)
            ), result
        )
    }
}