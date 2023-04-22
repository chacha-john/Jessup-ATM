package com.mirror.jessupbank.data

data class Customer(
    val account: Account,
    val company: Company,
    val firstName: String,
    val lastName: String,
    val pin: String,
)

class DefaultCustomers {
    companion object {
        fun provideCustomers(): List<Customer> {
            return listOf(
                Customer(
                    Account(
                        accountNumber = "1234567899",
                        accountType = "Personal",
                        balance = 100000,
                    ),
                    Company(
                        companyCode = "",
                        employeeId = "",
                    ),
                    firstName = "Thor",
                    lastName = "Odinson",
                    pin = "4321",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567890",
                        accountType = "Business",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "Stark Industries",
                        employeeId = "001",
                    ),
                    firstName = "Tony",
                    lastName = "Stark",
                    pin = "1234",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567891",
                        accountType = "Business",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "Avengers",
                        employeeId = "003",
                    ),
                    firstName = "Steve",
                    lastName = "Rogers",
                    pin = "1234",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567892",
                        accountType = "Personal",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "",
                        employeeId = "",
                    ),
                    firstName = "Bruce",
                    lastName = "Banner",
                    pin = "1234",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567893",
                        accountType = "Business",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "ABC",
                        employeeId = "212",
                    ),
                    firstName = "Jane",
                    lastName = "Doe",
                    pin = "1234",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567894",
                        accountType = "Business",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "ABC",
                        employeeId = "123",
                    ),
                    firstName = "Clint",
                    lastName = "Barton",
                    pin = "1234",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567895",
                        accountType = "Personal",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "",
                        employeeId = "",
                    ),
                    firstName = "Wanda",
                    lastName = "Allen",
                    pin = "1234",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567896",
                        accountType = "Personal",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "",
                        employeeId = "",
                    ),
                    firstName = "James",
                    lastName = "Rhodes",
                    pin = "1234",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567897",
                        accountType = "Business",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "AMC",
                        employeeId = "007",
                    ),
                    firstName = "Sam",
                    lastName = "Wilson",
                    pin = "1234",
                ),
                Customer(
                    Account(
                        accountNumber = "1234567898",
                        accountType = "Business",
                        balance = 1000000,
                    ),
                    Company(
                        companyCode = "XYZ",
                        employeeId = "234",
                    ),
                    firstName = "Alex",
                    lastName = "Smith",
                    pin = "1234",
                )

            )
        }
    }
}
