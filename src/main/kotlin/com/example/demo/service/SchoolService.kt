package com.example.demo.service

import com.example.demo.client.SchoolClient
import com.example.demo.model.School
import com.example.demo.repository.SchoolRepository
import org.springframework.stereotype.Service

@Service
class SchoolService( var schools: SchoolRepository, var schoolClient: SchoolClient) {

    fun getSchoolByNumber(id: Int): School {
        return schools.getSchoolById(id)
    }

    fun addSchool(school: School): School {
        val responseSchool = schoolClient.enrichSchoolInfo(school)
        schools.saveSchool(school)
        return responseSchool
    }

    fun getSchoolsWhereCountOfStudentsBiggerThan(count: Int) = schools.getSchoolsWhereCountOfStudentsBigger(count)
}