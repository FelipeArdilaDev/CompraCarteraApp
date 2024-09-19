package com.example.pruebatecnicaandroid.presentacion.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.pruebatecnicaandroid.R
import com.example.pruebatecnicaandroid.databinding.FragmentTransactionSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionSuccessFragment : Fragment() {

    private lateinit var binding: FragmentTransactionSuccessBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTransactionSuccessBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            with(binding) {
                tvBank.text = args.getString("name") ?: "Sin nombre"
                TxNumber.text = args.getString("txcNumber") ?: "Sin número de transacción"
                tvAmmountValue.text = args.getString("ammountValue") ?: "0.00"
                tvDateValue.text = args.getString("date") ?: "Sin fecha"
            }
        }

        setUpListener()
    }

    private fun setUpListener() {
        binding.btnFinalizar.setOnClickListener {
            findNavController().navigate(R.id.action_transactionSuccessFragment_to_portfolioPurchaseFragment)
        }

        binding.btnBack.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding.btnClose.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }

}