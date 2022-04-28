package com.example.demo


import com.example.demo.client.SchoolClient
import com.example.demo.model.School
import com.example.demo.repository.SchoolRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {
    private companion object {
        private val ResSchool1 = School(1, "Черкизовская 2", 34, 730)

    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var schoolClient: SchoolClient

    @MockkBean
    lateinit var schoolRepo: SchoolRepository


    @Test
    fun getSchoolById() {
        every { schoolRepo.getSchoolById(1) } returns ResSchool1
        mockMvc.perform(
            get
                ("/schools/1")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.address").value("Черкизовская 2"))
            .andExpect(jsonPath("$.countOfTeachers").value(34))
            .andExpect(jsonPath("$.countOfStudents").value(730))
    }
    @Test
    fun addSchoolToList() {

        coEvery { schoolClient.schoolTransform(any()) } returns ResSchool1

        mockMvc.perform(
            post("/schools")
                .param("id", "5")
                .param("address", "Черкизовская 2")
                .param("countOfTeachers", "34")
                .param("countOfStudents", "730")
        )
            .andExpect(status().isOk)
    }

}

