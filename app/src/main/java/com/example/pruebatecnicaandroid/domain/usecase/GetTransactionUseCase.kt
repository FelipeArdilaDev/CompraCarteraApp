package com.example.pruebatecnicaandroid.domain.usecase

import com.example.pruebatecnicaandroid.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(private val transactionRepostory: TransactionRepository) {
    fun execute(): String {
        return transactionRepostory.getTransactionNumber()
    }
}