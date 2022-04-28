package com.example.demo.service

import com.example.demo.client.SchoolClient
import com.example.demo.model.School
import com.example.demo.model.SchoolDto
import com.example.demo.repository.SchoolRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class SchoolService(var schools: SchoolRepository, var schoolClient: SchoolClient) {

    fun getSchoolByNumber(id: Int): School {
        return schools.getSchoolById(id)
    }

    fun addSchool(school: SchoolDto) {
        CoroutineScope(Dispatchers.Default).launch {
            val responseSchool = schoolClient.schoolTransform(school)
            withContext(Dispatchers.IO) {
                schools.saveSchool(responseSchool)
            }
        }
    }
}