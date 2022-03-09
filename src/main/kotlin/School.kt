class School(private var students: ArrayList<Person>,
            private var teachers: ArrayList<Person>,
            private var number: Int,
            private var address: String) {
    override fun toString(): String {
        return "Ученики: ${getPersonsInfo(students)}\nУчителя: ${getPersonsInfo(teachers)}\nНомер школы: $number\nАдресс школы: $address\n"
    }

    private fun getPersonsInfo(persons: ArrayList<Person>): ArrayList<String>{
        val list: ArrayList<String> = arrayListOf()
        persons.forEach { list.add(it.firstName+" "+it.lastName)}
        return list
    }
}