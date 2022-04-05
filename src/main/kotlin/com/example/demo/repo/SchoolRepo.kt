package com.example.demo.repo

import com.example.demo.models.School
import org.springframework.stereotype.Repository

@Repository
class SchoolRepo
{
    private var schools=
        mutableListOf(School(1,"Черкизовская 2",34,730),School(2,"Черкизовская 45",23,500),School(67,"Алохина 5",3,50))

    fun getSchoolByNumber(number: Int): School {
        return schools.first { it.number == number }?:
        throw IllegalArgumentException("Такой школы нет")
    }
    fun addSchool(school: School) {
        schools.add(school)
    }

    fun getSchoolByCount(count: Int): MutableList<School> {
        return schools.filter { it.countOfStudents>=count }.toMutableList()?:
        throw IllegalArgumentException("Таких школ нет")
    }
}