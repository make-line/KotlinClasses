package com.example.demo.model
import javax.persistence.*


@Entity
@Table(name = "schools")
data class School(
    @Id
    @Column(name = "id")
    var id: Int?=null,
    @Column(name = "address")
    var address: String?=null,
    @Column(name = "count_of_teachers")
    var countOfTeachers: Int?=null,
    @Column(name = "count_of_students")
    var countOfStudents: Int?=null
)