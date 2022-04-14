package com.example.demo.repository;

import com.example.demo.model.School
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service


@Service
//@Primary
class JdbcSchoolRepository(var jdbcTemplate: JdbcTemplate) : SchoolRepository {
    override fun saveSchool(school: School): School {
        jdbcTemplate.update(
            "merge into schools (address, count_of_students, count_of_teachers, id) key (id) values (?, ?, ?, ?)",
            school.address,
            school.countOfStudents,
            school.countOfTeachers,
            school.id
        )
        return school
    }


    override fun getSchoolById(id: Int): School =
        jdbcTemplate.queryForObject(
            "select * from schools where id = ?",
            DataClassRowMapper(School::class.java),
            id
        )

    override fun getSchoolsWhereCountOfStudentsBigger(count_of_students: Int): List<School> =
        jdbcTemplate.query(
            "select * from schools where count_of_students >?",

            DataClassRowMapper(School::class.java),
            count_of_students
        )

}