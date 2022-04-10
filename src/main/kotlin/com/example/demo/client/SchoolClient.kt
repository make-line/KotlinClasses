package com.example.demo.client

import com.example.demo.model.School

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SchoolClient(
    private val restTemplate: RestTemplate
) {
    @Value("\${school.address}")
    private lateinit var schoolAddress: String

    fun enrichSchoolInfo(requestSchool: School) =
        restTemplate.postForEntity(schoolAddress, requestSchool, School::class.java).body
            ?: throw IllegalStateException("Не удалось обогатить данные")
}