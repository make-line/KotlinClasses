//package com.example.demo
//
//
//import com.example.demo.client.SchoolClient
//import com.example.demo.exception.MyException
//import com.example.demo.model.School
//import com.example.demo.repository.SchoolRepository
//import com.fasterxml.jackson.databind.ObjectMapper
//import com.ninjasquad.springmockk.MockkBean
//import io.mockk.every
//import org.hamcrest.CoreMatchers.containsString
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class DemoApplicationTests {
//    private companion object {
//        private val ReqSchool1 = School(1, "Черкизовская 2", 34, 730)
//        private val ResSchool1 = School(1, "Черкизовская 2", 34, 730)
//        private val ResSchool2 = School(2, "Черкизовская 45", 23, 500)
//
//    }
//
//    @Autowired
//    private lateinit var mockMvc: MockMvc
//    private val objectMapper = ObjectMapper()
//
//    @MockkBean
//    private lateinit var schoolClient: SchoolClient
//
//    @MockkBean
//    lateinit var schoolRepo: SchoolRepository
//
//    @Test
//    fun getSchoolById() {
//        every { schoolRepo.getSchoolByNumber(any()) } returns ResSchool1
//        mockMvc.perform(
//            get
//                ("/schools/1")
//        )
//            .andExpect(status().isOk)
//            .andExpect(jsonPath("$.number").value(1))
//            .andExpect(jsonPath("$.address").value("Черкизовская 2"))
//            .andExpect(jsonPath("$.countOfTeachers").value(34))
//            .andExpect(jsonPath("$.countOfStudents").value(730))
//    }
//
//    @Test
//    fun getSchoolBycOUNTIdFail() {
//        every { schoolRepo.getSchoolByNumber(any()) } throws IllegalArgumentException("Такой школы нет")
//
//        mockMvc.perform(get("/schools/1"))
//            .andExpect(status().is4xxClientError)
//            .andExpect(content().string(containsString("Такой школы нет")))
//    }
//
//
//    @Test
//    fun addSchoolToList() {
//
//        every { schoolClient.enrichSchoolInfo(any()) } returns ResSchool1
//        every { schoolRepo.addSchool(any()) } returns Unit
//
//        mockMvc.perform(
//            post("/schools/add")
//                .content(objectMapper.writeValueAsString(ReqSchool1))
//                .contentType(MediaType.APPLICATION_JSON)
//        )
//            .andExpect(status().isOk)
//            .andExpect(jsonPath("$.number").value(1))
//            .andExpect(jsonPath("$.address").value("Черкизовская 2"))
//            .andExpect(jsonPath("$.countOfTeachers").value(34))
//            .andExpect(jsonPath("$.countOfStudents").value(730))
//    }
//
//    @Test
//    fun getSchoolByCount() {
//        every { schoolRepo.getSchoolsByCountOfStudentsBiggerThan(any()) } returns mutableListOf(ResSchool1, ResSchool2)
//        mockMvc.perform(
//            get
//                ("/schools").param("countOfStudents", "500")
//        )
//            .andExpect(status().isOk)
//            .andExpect(jsonPath("$[0].number").value(1))
//            .andExpect(jsonPath("$[0].address").value("Черкизовская 2"))
//            .andExpect(jsonPath("$[0].countOfTeachers").value(34))
//            .andExpect(jsonPath("$[0].countOfStudents").value(730))
//            .andExpect(jsonPath("$[1].number").value(2))
//            .andExpect(jsonPath("$[1].address").value("Черкизовская 45"))
//            .andExpect(jsonPath("$[1].countOfTeachers").value(23))
//            .andExpect(jsonPath("$[1].countOfStudents").value(500))
//
//    }
//
//    @Test
//    fun getSchoolByCountFail() {
//        every { schoolRepo.getSchoolsByCountOfStudentsBiggerThan(any()) } throws IllegalArgumentException("Нет школ с таким или более количеством учеников")
//        mockMvc.perform(get("/schools").param("countOfStudents", "800"))
//            .andExpect(status().is4xxClientError)
//            .andExpect(content().string(containsString("Нет школ с таким или более количеством учеников")))
//
//    }
//
//    @Test
//    fun serverErrorTest() {
//        every { schoolRepo.getSchoolsByCountOfStudentsBiggerThan(any()) } throws MyException("Ошибка на стороне сервера")
//        mockMvc.perform(get("/schools").param("countOfStudents", "800"))
//            .andExpect(status().is5xxServerError)
//            .andExpect(content().string(containsString("Ошибка на стороне сервера")))
//
//    }
//
//
//    @Test
//    fun contextLoads() {
//    }
//
//}
//
