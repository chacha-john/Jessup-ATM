package com.mirror.jessupbank.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mirror.jessupbank.R
import com.mirror.jessupbank.databinding.FragmentDashboardBinding
import com.mirror.jessupbank.utils.snack
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    private val viewModel: JessupViewModel by lazy {
        ViewModelProvider(requireActivity())[JessupViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return FragmentDashboardBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            "Welcome ${viewModel.loggedInCustomer?.firstName} ${viewModel.loggedInCustomer?.lastName}".also {
                tvGreeting.text = it
            }
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            tvQuit.setOnClickListener {
                requireActivity().finish()
            }

            cvBalanceEnquiry.setOnClickListener {
                viewModel.loggedInCustomer?.account?.balance.toString().also { textBalanceAmount.text = it }
                tilAmount.visibility = View.GONE
                btnContinue.visibility = View.GONE
                lifecycleScope.launch {
                    delay(5000)
                    textBalanceAmount.text = "******"
                }
            }

            cardViewDeposit.setOnClickListener {
                tilAmount.visibility = View.VISIBLE
                btnContinue.text = getString(R.string.action_deposit)
                btnContinue.visibility = View.VISIBLE

            }

            cardViewWithdraw.setOnClickListener {
                tilAmount.visibility = View.VISIBLE
                btnContinue.text = getString(R.string.action_withdraw)
                btnContinue.visibility = View.VISIBLE
            }
            btnContinue.setOnClickListener {
                val amount: Int = etAmount.text.toString().toInt()
                if (btnContinue.text == getString(R.string.action_deposit)) {
                    viewModel.loggedInCustomer?.account?.balance?.plus(amount)
                        ?.also { viewModel.loggedInCustomer?.account?.balance = it }
                } else {
//                    if new balance will not be below zero, then proceed
                    if (viewModel.loggedInCustomer?.account?.balance?.minus(amount)!! < 0) {
                        snack("Insufficient funds")
                        return@setOnClickListener
                    }
                    viewModel.loggedInCustomer?.account?.balance?.minus(amount)
                        ?.also { viewModel.loggedInCustomer?.account?.balance = it }
                }

                viewModel.loggedInCustomer?.account?.balance.toString().also { textBalanceAmount.text = it }
                etAmount.text?.clear()
                tilAmount.visibility = View.GONE
                btnContinue.visibility = View.GONE
                lifecycleScope.launch {
                    delay(5000)
                    textBalanceAmount.text = "******"
                }
            }
        }
    }

}