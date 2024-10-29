package com.example.pruebatecnicaandroid.presentacion.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.pruebatecnicaandroid.domain.repository.PurchaseResult
import com.example.pruebatecnicaandroid.domain.usecase.GetTransactionUseCase
import com.example.pruebatecnicaandroid.domain.usecase.ProcessPurchaseUseCase
import com.example.pruebatecnicaandroid.presentacion.views.ui.routes.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase,
    private val processPurchaseUseCase: ProcessPurchaseUseCase
) : ViewModel() {

    private val _transactionNumber = MutableLiveData<String>()
    val transactionNumber: LiveData<String> = _transactionNumber

    private val _PurchaseResult = MutableLiveData<PurchaseResult>()
    val PurchaseResult: LiveData<PurchaseResult> = _PurchaseResult

    fun fetchTransactionNumber(navigationController: NavHostController,name: String,
                               ammountValue: String) {
        viewModelScope.launch {
            delay(3000)
            val transactionNumber = getTransactionUseCase.execute()
            _transactionNumber.value = transactionNumber
            simulatePurhcase(transactionNumber, navigationController, name, ammountValue)
        }
    }

    private fun simulatePurhcase(
        transactionNumber: String,
        navigationController: NavHostController,
        name: String,
        ammountValue: String
    ) {
        viewModelScope.launch {
            _PurchaseResult.value = processPurchaseUseCase.execute(transactionNumber)

            if (_PurchaseResult.value!!.success) {
                navigationController.navigate(
                    Routes.SuccessScreen.createRoute(
                        name = name,
                        txcNumber = transactionNumber,
                        ammountValue = ammountValue,
                        date = _PurchaseResult.value!!.timestamp!!
                    )
                )
            }

        }

    }
}
