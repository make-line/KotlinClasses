package com.example.demo.repository

import com.example.demo.model.School
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service


@Service
@Primary
class JpaSchoolRepository(private val repository: SchoolJpaRepositoryInterface) : SchoolRepository {
    override fun saveSchool(school: School): School {
        return repository.save(school)
    }

    override fun getSchoolById(id: Int): School {
        return repository.getById(id)
    }

    override fun getSchoolsWhereCountOfStudentsBigger(count: Int): List<School> {
        return repository.getSchoolsByCountOfStudentsIsAfter(count)
    }
}