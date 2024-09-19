package com.example.pruebatecnicaandroid.data.repository

import com.example.pruebatecnicaandroid.domain.repository.TransactionRepository

class TransactionRepositoryMock : TransactionRepository {
    override fun getTransactionNumber(): String {
        return "1234567890"
    }
}