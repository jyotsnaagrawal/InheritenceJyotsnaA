import java.util.*

// Base class representing a Customer
open class Customer(
    private val customerName: String,
    private val customerPhone: String,
    private val customerAddress: String,
    val squareFootage: Double
) {
    // Open function to display customer information
    open fun displayInfo(): String {
        return """
            Customer: $customerName
            Phone: $customerPhone
            Address: $customerAddress
            Square Footage: $squareFootage sqft
        """.trimIndent()
    }
}

// Subclass representing a Residential customer, inheriting from Customer
class Residential(
    customerName: String,
    customerPhone: String,
    customerAddress: String,
    squareFootage: Double,
    private val senior: Boolean
) : Customer(customerName, customerPhone, customerAddress, squareFootage) {

    // Constants specific to Residential class
    private val rate: Double = 6.0

    // Overridden function to display Residential customer information
    override fun displayInfo(): String {
        return super.displayInfo() + """
            Rate: $$rate per 1000 sqft
            Senior Discount: ${if (senior) "Yes" else "No"}
            Weekly Charge: $${calculateWeeklyCharge()}
        """.trimIndent()
    }

    // Private function to calculate weekly charge for Residential customer
    private fun calculateWeeklyCharge(): Double {
        val discount = if (senior) 0.15 else 0.0
        return squareFootage / 1000 * rate * (1 - discount)
    }
}

// Subclass representing a Commercial customer, inheriting from Customer
class Commercial(
    customerName: String,
    customerPhone: String,
    customerAddress: String,
    squareFootage: Double,
    private val name: String,
    private val multiProperty: Boolean
) : Customer(customerName, customerPhone, customerAddress, squareFootage) {

    // Constants specific to Commercial class
    private val rate: Double = 5.0

    // Overridden function to display Commercial customer information
    override fun displayInfo(): String {
        return super.displayInfo() + """
            Commercial Name: $name
            Rate: $$rate per 1000 sqft
            Multiple Properties: ${if (multiProperty) "Yes" else "No"}
            Weekly Charge: $${calculateWeeklyCharge()}
        """.trimIndent()
    }

    // Private function to calculate weekly charge for Commercial customer
    private fun calculateWeeklyCharge(): Double {
        val discount = if (multiProperty) 0.1 else 0.0
        return squareFootage / 1000 * rate * (1 - discount)
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    var option: Int
    do {
        // Display menu options to the user
        println("1. Residential Customer")
        println("2. Commercial Customer")
        println("3. Done")
        print("Choose an option: ")
        option = scanner.nextInt()

        // User selection based on menu options
        when (option) {
            1 -> {
                println("Enter customer details:")
                print("Name: ")
                val name = scanner.next()
                print("Phone: ")
                val phone = scanner.next()
                print("Address: ")
                val address = scanner.next()
                print("Square Footage: ")
                val sqft = scanner.nextDouble()
                print("Senior (true/false): ")
                val senior = scanner.nextBoolean()

                // Create Residential customer object and display information
                val residential = Residential(name, phone, address, sqft, senior)
                println(residential.displayInfo())
            }
            2 -> {
                println("Enter customer details:")
                print("Name: ")
                val name = scanner.next()
                print("Phone: ")
                val phone = scanner.next()
                print("Address: ")
                val address = scanner.next()
                print("Square Footage: ")
                val sqft = scanner.nextDouble()
                print("Multiple Properties (true/false): ")
                val multiProperty = scanner.nextBoolean()

                // Create Commercial customer object and display information
                val commercial = Commercial(name, phone, address, sqft, name, multiProperty)
                println(commercial.displayInfo())
            }
            3 -> println("Exiting...")
            else -> println("Invalid option. Please try again.")
        }
    } while (option != 3)
}
