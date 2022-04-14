package com.example.demo.repository

import com.example.demo.model.School
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SchoolJpaRepository : JpaRepository<School, Int> {
    fun getSchoolsByCountOfStudentsIsAfter(int: Int): List<School>
}