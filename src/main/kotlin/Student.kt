data class Student(override var firstName: String,
              override var lastName: String,
              override var age: Int,
              private var gpa: Double,
              private var grade: Int,
              private var idCard: IdCard): Person() {

    override fun getInfo(): String {
        return ("Имя: $firstName\nФамилия: $lastName\nВозраст: $age\nСредняя оценка: $gpa\nКласс: $grade\nКарта: $idCard\n")
    }
    fun findGPA(marks: Array<Double>){
        var sum = 0.0
        for (i in marks){
            sum += i
        }
        this.gpa = sum/marks.size
    }
    //Перегрузка
    fun findGPA(status: String){
        if (status == "NO"){
            this.gpa = 0.0
        }
        else{
            println("ошибка записи")
        }
    }
}