package com.example.demo.model

import java.io.Serializable

data class SchoolDto(
    val id: Int? = null,
    val address: String? = null,
    val countOfTeachers: Int? = null,
    val countOfStudents: Int? = null
) : Serializable
