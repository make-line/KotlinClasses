fun main(){
    val idCardForStudents1 = IdCard(100001, 1)
    val idCardForStudents2 = IdCard(100002, 1)
    val idCardForTeachers = IdCard(200002, 2)

    val student1: Person = Student("Петя", "Иванов", 13, 3.0, 5, idCardForStudents1)
    val student2 = Student("Лиза", "Петрова", 15, 4.0, 7, idCardForStudents2)
    val teacher1: Person = Teacher("Екатерина", "Игоревна", 56, "История", idCardForTeachers)

    val persons: ArrayList<Person> = arrayListOf(student1, teacher1)
    for (person in persons){
        println(person.getInfo() + "\n")
    }
    val school = School(arrayListOf(student1, student2), arrayListOf(teacher1), 1973, "Ул. Ленина, дом 36")
    println(school)

    println(student2.getInfo() + "\n")
    student2.findGPA(arrayOf(1.0, 2.0, 5.0))
    println(student2.getInfo() + "\n")
    student2.findGPA("NO")
    println(student2.getInfo())
}
