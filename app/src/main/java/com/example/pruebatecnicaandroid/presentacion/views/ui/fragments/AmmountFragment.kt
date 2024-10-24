package com.example.pruebatecnicaandroid.presentacion.views.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.pruebatecnicaandroid.R
import com.example.pruebatecnicaandroid.data.local.PreferencesHelper
import com.example.pruebatecnicaandroid.databinding.FragmentAmmountBinding
import com.example.pruebatecnicaandroid.presentacion.viewmodel.TransactionViewModel
import com.example.pruebatecnicaandroid.utils.DPUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AmmountFragment : Fragment() {

    private var ammount = 0L
    private var name: String? = null
    private var ammountValue: String? = null
    private var txcNumber: String? = null

    private val viewModel: TransactionViewModel by viewModels()

    private lateinit var binding: FragmentAmmountBinding
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAmmountBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferencesHelper = PreferencesHelper(requireContext())
        binding.btnComprar.isEnabled = false

        ammount = preferencesHelper.getBalance()
        binding.fieldAmmounINput.helperText =
            "Ingrese un monto inferior a ${DPUtils.formatCurrency(ammount.toDouble())} (cupo disponible)"

        name = arguments?.getString("name")
        binding.tvOrigenBank.text = name

        setupListenerTextWatcher(ammount)
        setupListener()
    }

    private fun setupObservers() {
        viewModel.transactionNumber.observe(viewLifecycleOwner) { transactionNumber ->
            Log.e("TAG", "setupObservers: $transactionNumber")
            txcNumber = transactionNumber
        }

        viewModel.PurchaseResult.observe(viewLifecycleOwner) { purchaseResult ->
            binding.progress.visibility = View.VISIBLE
            if (purchaseResult.success) {
                val bundle = Bundle().apply {
                    putString("name", name)
                    putString("txcNumber", txcNumber)
                    putString("ammountValue", ammountValue)
                    putString("date", purchaseResult.timestamp)
                }
                findNavController().navigate(
                    R.id.action_ammountFragment2_to_transactionSuccessFragment,
                    bundle
                )
            } else {
                Toast.makeText(requireContext(), "Fallo en la compra", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun setupListener() {
        binding.btnComprar.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            viewModel.fetchTransactionNumber()
            setupObservers()
        }

        binding.btnBack.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding.btnClose.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }

    private fun setupListenerTextWatcher(ammount: Long) {
        binding.etAmmoun.addTextChangedListener(object : TextWatcher {
            private var isEditing = false
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //no se usa
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //no se usa

            }

            override fun afterTextChanged(editable: Editable?) {
                if (isEditing) return

                val inputText = editable.toString()
                val cleanString = inputText.replace("[^\\d]".toRegex(), "")
                val parsed = cleanString.toDoubleOrNull() ?: 0.0
                val formattedInput = DPUtils.formatCurrency(parsed)
                ammountValue = formattedInput

                isEditing = true
                binding.etAmmoun.setText(formattedInput)
                binding.etAmmoun.setSelection(formattedInput.length)
                isEditing = false

                if (parsed > ammount) {
                    binding.fieldAmmounINput.error =
                        "El monto no debe superar el cupo disponible de ${
                            DPUtils.formatCurrency(
                                ammount.toDouble()
                            )
                        }"
                } else {
                    binding.btnComprar.isEnabled = true
                    binding.fieldAmmounINput.error = null
                }
            }

        })
    }
}