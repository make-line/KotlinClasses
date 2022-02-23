class Teacher(override var firstName: String,
              override var lastName: String,
              override var age: Int,
              private var lesson: String,
              private var idCard: IdCard): Person() {

    override fun getInfo(): String {
        return ("Имя: $firstName\nФамилия: $lastName\nВозраст: $age\nДисциплина: $lesson\nКарта: $idCard\n")
    }
}