package com.example.demo.services

import com.example.demo.client.SchoolClient
import com.example.demo.models.School
import com.example.demo.models.SchoolDTO
import com.example.demo.repo.SchoolRepo
import org.springframework.stereotype.Service

@Service
class SchoolService(var schools: SchoolRepo, var schoolClient: SchoolClient) {

    fun getSchoolByNumber(id:Int): School{
        return schools.getSchoolByNumber(id)
    }

    fun addSchool(school: SchoolDTO): School {
        val responseSchool = schoolClient.enrichSchoolInfo(school)
        schools.addSchool(responseSchool)
        return responseSchool
    }

    fun getSchoolWhereCountOfStudentsBiggerThan(count:Int)=schools.getSchoolByCount(count)
}