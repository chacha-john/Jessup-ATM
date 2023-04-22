package com.mirror.jessupbank.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mirror.jessupbank.R
import com.mirror.jessupbank.data.Customer
import com.mirror.jessupbank.databinding.FragmentWelcomeBinding
import com.mirror.jessupbank.utils.snack

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    private val viewModel: JessupViewModel by lazy {
        ViewModelProvider(requireActivity())[JessupViewModel::class.java]
    }

    private var customer: Customer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return FragmentWelcomeBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnContinue.setOnClickListener {
                val accountNumber = etAccountNumber.text.toString()
                val pin = etPin.text.toString()
                val companyCode = etCompanyCode.text.toString()
                val employeeId = etEmployeeId.text.toString()

                if (accountNumber.isEmpty()) {
                    snack("Please enter your account number")
                    return@setOnClickListener
                } else {
                    getCustomer(accountNumber)
                }
            }


        }

    }

    // account lookup
    private fun getCustomer(accountNumber: String) {
        customer =  viewModel.getCustomer(accountNumber)

        if (customer == null) {
            snack("Account number not found")
            binding.etAccountNumber.text?.clear()
        } else {
            binding.apply {
                tvPin.visibility = View.VISIBLE
                tilPin.visibility = View.VISIBLE
                tvCompanyCode.visibility = View.GONE
                tilCompanyCode.visibility = View.GONE
                tvEmployeeId.visibility = View.GONE
                tilEmployeeId.visibility = View.GONE
                btnContinue.setOnClickListener {
                    login()
                }
            }
            if (customer?.account?.accountType == "Business"){
                binding.apply {
                    tvCompanyCode.visibility = View.VISIBLE
                    tilCompanyCode.visibility = View.VISIBLE
                    tvEmployeeId.visibility = View.VISIBLE
                    tilEmployeeId.visibility = View.VISIBLE
                    btnContinue.setOnClickListener {
                        login()
                    }
                }
            }


        }
    }

//    login
    private fun login() {
        val pin = binding.etPin.text.toString()
        val companyCode = binding.etCompanyCode.text.toString()
        val employeeId = binding.etEmployeeId.text.toString()

        if (pin.isEmpty()) {
            snack("Please enter your pin")
            return
        } else if (companyCode.isEmpty() && customer?.account?.accountType == "Business") {
            snack("Please enter your company code")
            return
        } else if (employeeId.isEmpty() && customer?.account?.accountType == "Business") {
            snack("Please enter your employee id")
            return
        } else {
            if (pin == customer?.pin) {
                if (customer?.account?.accountType == "Business") {
                    if (companyCode == customer?.company?.companyCode && employeeId == customer?.company?.employeeId) {
                        viewModel.loggedInCustomer = customer
                        findNavController().navigate(R.id.action_welcomeFragment_to_dashboardFragment)
                    } else {
                        snack("Invalid credentials")
                    }
                } else {
                    viewModel.loggedInCustomer = customer
                    findNavController().navigate(R.id.action_welcomeFragment_to_dashboardFragment)
                }
            } else {
                snack("Invalid credentials")
            }
        }
    }
}