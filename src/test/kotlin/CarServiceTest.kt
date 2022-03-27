import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CarServiceTest {
    @Test
    fun `проверка метода translateAndSortByPrice`() {
        val carList = listOf<Car>(
            Car(
                "Ленд Крузер 300",
                "Тойота",
                "Внедорожник",
                15720000.00,
                9.00
            ),
            Car(
                "Родстер",
                "Тесла",
                "Купе",
                20000000.00,
                0.00
            ),
            Car(
                "Серия 7",
                "БМВ",
                "Седан",
                10650000.00,
                7.00
            )
        )
        val carService = CarService()
        val carListResult = listOf<Car>(
            Car(
                "Series 7",
                "BMW",
                "Sedan",
                106500.00,
                7.00
            ),
            Car(
                "Land Cruiser 300",
                "Toyota",
                "SUV",
                157200.00,
                9.00
            ),
            Car(
                "Roadster",
                "Tesla",
                "Coupe",
                200000.00,
                0.00
            )
        )
        assertEquals(carListResult, carService.translateAndSortByPrice(carList))
    }

    @Test
    fun `проверка метода translateAndSortByPriceSeq`() {
        val carList = listOf<Car>(
            Car(
                "Ленд Крузер 300",
                "Тойота",
                "Внедорожник",
                15720000.00,
                9.00
            ),
            Car(
                "Родстер",
                "Тесла",
                "Купе",
                20000000.00,
                0.00
            ),
            Car(
                "Серия 7",
                "БМВ",
                "Седан",
                10650000.00,
                7.00
            )
        )
        val carService = CarService()
        val carListResult = listOf<Car>(
            Car(
                "Series 7",
                "BMW",
                "Sedan",
                106500.00,
                7.00
            ),
            Car(
                "Land Cruiser 300",
                "Toyota",
                "SUV",
                157200.00,
                9.00
            ),
            Car(
                "Roadster",
                "Tesla",
                "Coupe",
                200000.00,
                0.00
            )
        )
        assertEquals(carListResult, carService.translateAndSortByPriceSeq(carList))
    }

    @Test
    fun `проверка метода carGroup`() {
        val carList = listOf<Car>(
            Car(
                "Ленд Крузер 300",
                "Тойота",
                "Внедорожник",
                15720000.00,
                9.00
            ),
            Car(
                "Родстер",
                "Тесла",
                "Седан",
                20000000.00,
                0.00
            ),
            Car(
                "Серия 7",
                "БМВ",
                "Седан",
                10650000.00,
                7.00
            )
        )
        val carService = CarService()
        val carListResult = mapOf<String, List<Car>>(
            "Внедорожник" to listOf(
                Car(
                    "Ленд Крузер 300",
                    "Тойота",
                    "Внедорожник",
                    15720000.00,
                    9.00
                )
            ),
            "Седан" to listOf(
                Car(
                    "Родстер",
                    "Тесла",
                    "Седан",
                    20000000.00,
                    0.00
                ),
                Car(
                    "Серия 7",
                    "БМВ",
                    "Седан",
                    10650000.00,
                    7.00
                )
            )
        )
        assertEquals(carListResult, carService.carGroup(carList, "carType"))
    }

    @Test
    fun `проверка метода getFirstThreeFilteredItems`() {
        val carList = listOf<Car>(
            Car(
                "Ленд Крузер 300",
                "Тойота",
                "Внедорожник",
                10000000.00,
                9.00
            ),
            Car(
                "Родстер",
                "Тесла",
                "Седан",
                10000000.00,
                0.00
            ),
            Car(
                "Серия 7",
                "БМВ",
                "Седан",
                10000000.00,
                7.00
            ),
            Car(
                "Серия 7",
                "БМВ",
                "Купе",
                10000000.00,
                8.00
            )
        )
        val carService = CarService()
        assertEquals(
            listOf("Ленд Крузер 300", "Родстер", "Серия 7"),
            carService.getFirstThreeFilteredItems(carList) { Car -> Car.price == 10000000.00 })
    }

    @Test
    fun `проверка метода getFirstThreeFilteredItemsSeq`() {
        val carList = listOf<Car>(
            Car(
                "Ленд Крузер 300",
                "Тойота",
                "Внедорожник",
                10000000.00,
                9.00
            ),
            Car(
                "Родстер",
                "Тесла",
                "Седан",
                10000000.00,
                0.00
            ),
            Car(
                "Серия 7",
                "БМВ",
                "Седан",
                10000000.00,
                7.00
            ),
            Car(
                "Серия 7",
                "БМВ",
                "Купе",
                10000000.00,
                8.00
            )
        )
        val carService = CarService()
        assertEquals(
            listOf("Ленд Крузер 300", "Родстер", "Серия 7"),
            carService.getFirstThreeFilteredItemsSeq(carList) { Car -> Car.price == 10000000.00 })
    }
}
