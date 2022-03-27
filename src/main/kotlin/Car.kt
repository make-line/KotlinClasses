import java.lang.Exception

data class Car(
    val carName: String,
    val brand: String,
    val carType: String,
    var price: Double,
    val fuelConsumptionPer100km: Double
) {
}

class CarService() {

    fun translateAndSortByPrice(collection: Collection<Car>): Collection<Car> {
        var newCollection = mutableListOf<Car>()
        collection.forEach {
            var car = Car(
                translateToEnglish(it.carName),
                translateToEnglish(it.brand),
                translateToEnglish(it.carType),
                currencyConverting(it.price),
                it.fuelConsumptionPer100km
            )
            newCollection.add(car)
        }
        return newCollection.sortedBy { it.price }.toList()
    }

    private fun translateToEnglish(string: String): String {
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
            else -> {
                "$string (No translate)"
            }
        }
    }

    private fun currencyConverting(price: Double): Double {
        return (price / 100)
    }

    fun carGroup(collection: Collection<Car>, param: String): Map<String, List<Car>> {
        var collectionGrouping = mutableListOf<Car>()
        collection.forEach {
            var car = Car(
                it.carName,
                it.brand,
                it.carType,
                it.price,
                it.fuelConsumptionPer100km
            )
            collectionGrouping.add(car)
        }
        return when (param) {
            "carName" -> collectionGrouping.groupBy { it.carName }
            "brand" -> collectionGrouping.groupBy { it.brand }
            "carType" -> collectionGrouping.groupBy { it.carType }
            "price" -> collectionGrouping.groupBy { it.price.toString() }
            "fuelConsumptionPer100km" -> collectionGrouping.groupBy { it.fuelConsumptionPer100km.toString() }
            else -> {
                collectionGrouping.groupBy { it.carName }
            }
        }
    }

    fun getFirstThreeFilteredItems(collection: Collection<Car>, predicate: (Car) -> Boolean) =
        collection.asSequence()
            .filter(predicate)
            .map { it.carName }
            .take(3)
            .toList()

    fun getFirstThreeFilteredItemsSeq(collection: Collection<Car>, predicate: (Car) -> Boolean) =
        collection
            .filter(predicate)
            .map { it.carName }
            .take(3)
            .toList()

    fun translateAndSortByPriceSeq(collection: Collection<Car>): Collection<Car> =
        collection.asSequence().sortedBy { it.price }.map {
            Car(
                translateToEnglish(it.carName),
                translateToEnglish(it.brand),
                translateToEnglish(it.carType),
                currencyConverting(it.price),
                it.fuelConsumptionPer100km
            )
        }.toList()

    fun carGroupByType(collection: Collection<Car>): Map<String, List<Car>> =
        collection.groupBy { it.carType }

}
