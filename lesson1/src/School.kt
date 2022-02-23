class School(private var students: ArrayList<Person>,
            private var teachers: ArrayList<Person>,
            private var number: Int,
            private var address: String) {
    override fun toString(): String {
        return "Ученики: ${studentsInfo()}\nУчителя: ${teacherInfo()}\nНомер школы: $number\nАдресс школы: $address\n"
    }

    private fun studentsInfo(): ArrayList<String>{
        val studentsList: ArrayList<String> = arrayListOf()
        students.forEach { studentsList.add(it.firstName+" "+it.lastName)}
        return studentsList
    }
    private fun teacherInfo(): ArrayList<String>{
        val teacherList: ArrayList<String> = arrayListOf()
        teachers.forEach { teacherList.add(it.firstName+" "+it.lastName)}
        return teacherList
    }
}