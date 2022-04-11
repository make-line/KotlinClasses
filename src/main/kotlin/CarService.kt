class CarService() {

    fun translateAndSortByPrice(collection: Collection<Car>): Collection<Car> = collection.map {
        Car(
            translateToEnglish(it.name),
            translateToEnglish(it.brand),
            translateToEnglish(it.type),
            currencyConverting(it.price),
            it.fuelConsumption
        )
    }.sortedBy { it.price }.toList()


    fun carGroup(collection: Collection<Car>, param: String): Map<String, List<Car>> =
        when (param) {
            "name" -> collection.groupBy { it.name }
            "brand" -> collection.groupBy { it.brand }
            "type" -> collection.groupBy { it.type }
            "price" -> collection.groupBy { it.price.toString() }
            "fuelConsumptionPer100km" -> collection.groupBy { it.fuelConsumption.toString() }
            else -> {
                collection.groupBy { it.name }
            }
        }

    fun getFirstThreeFilteredItems(collection: Collection<Car>, predicate: (Car) -> Boolean) =
        collection.asSequence()
            .filter(predicate)
            .map { it.name }
            .take(3)
            .toList()

    fun getFirstThreeFilteredItemsSeq(collection: Collection<Car>, predicate: (Car) -> Boolean) =
        collection
            .filter(predicate)
            .map { it.name }
            .take(3)
            .toList()

    fun translateAndSortByPriceSeq(collection: Collection<Car>): Collection<Car> =
        collection.asSequence().sortedBy { it.price }.map {
            Car(
                translateToEnglish(it.name),
                translateToEnglish(it.brand),
                translateToEnglish(it.type),
                currencyConverting(it.price),
                it.fuelConsumption
            )
        }.toList()

    fun carGroupByType(collection: Collection<Car>): Map<String, List<Car>> =
        collection.groupBy { it.type }

}
