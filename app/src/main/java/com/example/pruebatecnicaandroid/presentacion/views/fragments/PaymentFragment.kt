package com.example.pruebatecnicaandroid.presentacion.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.pruebatecnicaandroid.R
import com.example.pruebatecnicaandroid.databinding.FragmentPaymentBinding
import com.example.pruebatecnicaandroid.presentacion.viewmodel.CardViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private val viewModel: CardViewModel by viewModels()
    private lateinit var binding: FragmentPaymentBinding
    private var lastValidCountryAlpha2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCheckBin.isEnabled = false
        setupListeners()
        isLoadingObserver()
    }

    private fun isLoadingObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }
    }

    private fun setupListeners() {
        binding.edtNumberCard.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No se necesita
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val cardNumber = s.toString()
                if (cardNumber.length in 15..16) {
                    binding.labelTextCard.error = null
                    binding.btnCheckBin.isEnabled = true
                } else {
                    binding.labelTextCard.error =
                        "El número debe tener una longitud de 15 a 16 números"
                    binding.btnCheckBin.isEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // No se necesita
            }
        })

        binding.btnCheckBin.setOnClickListener {
            val cardNumber = binding.edtNumberCard.text.toString()
            val bin = cardNumber.take(6)
            viewModel.validateBinAndProceed(bin)

            setupObserver()
        }

        binding.btnBack.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding.btnClose.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }

    private fun setupObserver() {
        viewModel.binInfo.observe(viewLifecycleOwner) { binInfo ->
            val countryAlpha2 = binInfo?.BIN?.country?.alpha2
            if (countryAlpha2 != null && countryAlpha2 != lastValidCountryAlpha2) {
                lastValidCountryAlpha2 = countryAlpha2
                if (countryAlpha2 == "CO") {
                    val bundle = Bundle().apply {
                        putString("name", binInfo.BIN.issuer?.name)
                    }
                    findNavController().navigate(
                        R.id.action_paymentFragment_to_ammountFragment2,
                        bundle
                    )
                } else {
                    popupError()
                }
            }
        }
    }

    private fun popupError() {
        MaterialAlertDialogBuilder(requireActivity(), R.style.CustomAlertDialogTheme)
            .setTitle("Ups")
            .setMessage("El numero de tarjeta no parece ser emitida en colombia, revise e intente neuvamente")
            .setPositiveButton("aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }

}