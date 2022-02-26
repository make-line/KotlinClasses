class IdCard (private var id: Int,
              private var access: Int) {

    override fun toString(): String {
        return "id: $id, доступ: $access"
    }
}