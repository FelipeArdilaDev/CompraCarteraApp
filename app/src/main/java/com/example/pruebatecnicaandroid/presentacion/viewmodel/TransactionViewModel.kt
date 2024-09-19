package com.example.pruebatecnicaandroid.presentacion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicaandroid.domain.repository.PurchaseResult
import com.example.pruebatecnicaandroid.domain.usecase.GetTransactionUseCase
import com.example.pruebatecnicaandroid.domain.usecase.ProcessPurchaseUseCase
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

    fun fetchTransactionNumber() {
        viewModelScope.launch {
            delay(3000)
            val transactionNumber = getTransactionUseCase.execute()
            _transactionNumber.value = transactionNumber
            simulatePurhcase(transactionNumber)
        }
    }

    private fun simulatePurhcase(transactionNumber: String) {
        viewModelScope.launch {
            _PurchaseResult.value = processPurchaseUseCase.execute(transactionNumber)
        }

    }
}
