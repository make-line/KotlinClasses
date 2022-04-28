package com.example.demo.controller

import com.example.demo.model.School
import com.example.demo.model.SchoolDto
import com.example.demo.service.SchoolService
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schools")
class RestController(val schoolService: SchoolService) {
    @GetMapping("/{id}")
    fun getSchoolByNumber(@PathVariable id: Int) =
        schoolService.getSchoolByNumber(id)

    @PostMapping
    fun addSchool(school: SchoolDto) =
        schoolService.addSchool(school)
}