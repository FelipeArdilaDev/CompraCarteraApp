package com.example.pruebatecnicaandroid.presentacion.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pruebatecnicaandroid.R
import com.example.pruebatecnicaandroid.data.local.PreferencesHelper
import com.example.pruebatecnicaandroid.databinding.FragmentPortfolioPurchaseBinding
import com.example.pruebatecnicaandroid.domain.usecase.GetUserInfoUseCase
import com.example.pruebatecnicaandroid.presentacion.viewmodel.DashboardViewModel
import com.example.pruebatecnicaandroid.presentacion.viewmodel.DashboardViewModelFactory
import com.example.pruebatecnicaandroid.utils.DPUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PortfolioPurchaseFragment : Fragment() {


    @Inject
    lateinit var getUserInfoUseCase: GetUserInfoUseCase

    private lateinit var preferencesHelper: PreferencesHelper


    private val viewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory(getUserInfoUseCase)
    }
    private lateinit var binding: FragmentPortfolioPurchaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPortfolioPurchaseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferencesHelper = PreferencesHelper(requireContext())

        setupObservers()
        setupListeners()
        viewModel.loadUserInfo()
    }

    private fun setupListeners() {
        binding.checkButton.setOnClickListener {
            findNavController().navigate(R.id.action_portfolioPurchaseFragment_to_paymentFragment)
        }
    }

    private fun setupObservers() {
        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            binding.tvUserName.text = userInfo.name
            val balance = DPUtils.formatCurrency(userInfo.balance.toDouble())
            binding.tvBalance.text = balance
            preferencesHelper.saveBalance(userInfo.balance)
            binding.tvCardNumber.text = userInfo.cardNumber
        }

    }
}