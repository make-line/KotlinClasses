package com.example.demo


import com.example.demo.client.SchoolClient
import com.example.demo.models.School
import com.example.demo.models.SchoolDTO
import com.example.demo.repo.SchoolRepo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockkObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*
import com.ninjasquad.springmockk.MockkBean
import org.hamcrest.CoreMatchers.containsString
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import kotlin.NoSuchElementException

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {
    private companion object {
        private val ReqSchool1 = SchoolDTO(1, "Черкизовская 2", 34, 730)
        private val ResSchool1 = School(1, "Черкизовская 2", 34, 730)
        private val ResSchool2 = School(2, "Черкизовская 45", 23, 500)

    }

    @Autowired
    private lateinit var mockMvc: MockMvc
    private val objectMapper = ObjectMapper()
    @MockkBean
    private lateinit var schoolClient: SchoolClient

    @MockkBean
    lateinit var schoolRepo: SchoolRepo

    @Test
    fun getSchoolById() {
        every { schoolRepo.getSchoolByNumber(any()) } returns ResSchool1
        mockMvc.perform(get
            ("/schools/1")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.number").value(1))
            .andExpect(jsonPath("$.address").value("Черкизовская 2"))
            .andExpect(jsonPath("$.countOfTeachers").value(34))
            .andExpect(jsonPath("$.countOfStudents").value(730))
    }

    @Test
    fun getSchoolBycOUNTIdFail() {
        every { schoolRepo.getSchoolByNumber(any()) } throws  IllegalArgumentException("Такой школы нет")

        mockMvc.perform(get("/schools/1"))
            .andExpect(status().is4xxClientError)
            .andExpect(content().string(containsString("Такой школы нет")))
    }


    @Test
    fun addSchoolToList() {

        every { schoolClient.enrichSchoolInfo(any()) } returns ResSchool1
        every { schoolRepo.addSchool(any()) } returns Unit

        mockMvc.perform(
            post("/schools/add")
                .content(objectMapper.writeValueAsString(ReqSchool1))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.number").value(1))
            .andExpect(jsonPath("$.address").value("Черкизовская 2"))
            .andExpect(jsonPath("$.countOfTeachers").value(34))
            .andExpect(jsonPath("$.countOfStudents").value(730))
    }
    @Test
    fun getSchoolByCount(){
        every {schoolRepo.getSchoolByCount(any())  } returns mutableListOf(ResSchool1, ResSchool2)
        mockMvc.perform(get
            ("/schools").param("count_of_students","500")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].number").value(1))
            .andExpect(jsonPath("$[0].address").value("Черкизовская 2"))
            .andExpect(jsonPath("$[0].countOfTeachers").value(34))
            .andExpect(jsonPath("$[0].countOfStudents").value(730))
            .andExpect(jsonPath("$[1].number").value(2))
            .andExpect(jsonPath("$[1].address").value("Черкизовская 45"))
            .andExpect(jsonPath("$[1].countOfTeachers").value(23))
            .andExpect(jsonPath("$[1].countOfStudents").value(500))

    }
    @Test
    fun getSchoolByCountFail(){
        every {schoolRepo.getSchoolByCount(any()) } throws  IllegalArgumentException("Во всех школах меньшее количество учеников")
        mockMvc.perform(get("/schools").param("count_of_students","800"))
            .andExpect(status().is4xxClientError)
            .andExpect(content().string(containsString("Во всех школах меньшее количество учеников")))

    }





        @Test
        fun contextLoads() {
        }

    }

