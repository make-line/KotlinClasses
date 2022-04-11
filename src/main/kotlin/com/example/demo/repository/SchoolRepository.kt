package com.example.demo.repository

import com.example.demo.model.School
import org.springframework.stereotype.Repository

@Repository
class SchoolRepository {
    private var schools =
        mutableListOf(
            School(1, "Черкизовская 2", 34, 730),
            School(2, "Черкизовская 45", 23, 500),
            School(67, "Алохина 5", 3, 50)
        )

    fun getSchoolByNumber(number: Int): School {
        return schools.firstOrNull { it.number == number } ?: throw IllegalArgumentException("Такой школы нет")
    }

    fun addSchool(school: School) {
        schools.add(school)
    }

    fun getSchoolsByCountOfStudentsBiggerThan(count: Int): MutableList<School> {
        if (schools.filter { it.countOfStudents >= count }.toMutableList().isEmpty())
            throw IllegalArgumentException("Таких школ нет")
        else return schools.filter { it.countOfStudents >= count }.toMutableList()

    }
}