fun translateToEnglish(string: String): String {
    return when (string) {
        "Тесла" -> "Tesla"
        "БМВ" -> "BMW"
        "Тойота" -> "Toyota"
        "Родстер" -> "Roadster"
        "Серия 7" -> "Series 7"
        "Ленд Крузер 300" -> "Land Cruiser 300"
        "Седан" -> "Sedan"
        "Внедорожник" -> "SUV"
        "Купе" -> "Coupe"
        else -> throw NoTranslateException()
    }
}

fun currencyConverting(price: Double): Double {
    return (price / 100)
}