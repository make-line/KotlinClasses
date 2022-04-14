package com.example.demo.repository

import com.example.demo.model.School
import org.springframework.stereotype.Repository


@Repository
interface SchoolRepository {
    fun saveSchool(school: School): School
    fun getSchoolById(id: Int): School
    fun getSchoolsWhereCountOfStudentsBigger(count: Int): List<School>
}
