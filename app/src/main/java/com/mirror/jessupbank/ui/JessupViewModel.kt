package com.mirror.jessupbank.ui

import androidx.lifecycle.ViewModel
import com.mirror.jessupbank.data.Customer
import com.mirror.jessupbank.data.DefaultCustomers

class JessupViewModel: ViewModel() {
    private val customers: List<Customer> = DefaultCustomers.provideCustomers()

//    logged in customer variable
    var loggedInCustomer: Customer? = null

    fun getCustomer(accountNumber: String, pin: String): Customer? {
        return customers.find { it.account.accountNumber == accountNumber && it.pin == pin }
    }

    // look for customer by account number
    fun getCustomer(accountNumber: String): Customer? {
        return customers.find { it.account.accountNumber == accountNumber }
    }


}