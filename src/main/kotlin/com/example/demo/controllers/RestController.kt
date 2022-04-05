package com.example.demo.controllers

import com.example.demo.models.School
import com.example.demo.models.SchoolDTO
import com.example.demo.services.SchoolService
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schools")
class RestController(val schoolService: SchoolService) {
    @GetMapping("/{id}")
    fun getSchoolByNumber(@PathVariable id: Int)=
        schoolService.getSchoolByNumber(id)
    @PostMapping("/add")
    fun addSchool(@RequestBody school: SchoolDTO)=
        schoolService.addSchool(school)
    @GetMapping
    fun getSchoolWhereCountOfStudentsBiggerThan(@RequestParam count_of_students:Int)=schoolService.getSchoolWhereCountOfStudentsBiggerThan(count_of_students)
}