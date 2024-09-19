package com.example.pruebatecnicaandroid.domain.repository

import com.example.pruebatecnicaandroid.data.repository.TransactionRepositoryMock
import com.example.pruebatecnicaandroid.domain.usecase.GetTransactionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionRepositoryModule {

    @Provides
    @Singleton
    fun provideTransactionRepository(): TransactionRepository {
        return TransactionRepositoryMock()
    }

    @Provides
    @Singleton
    fun provideGetTransactionUseCase(transactionRepository: TransactionRepository): GetTransactionUseCase {
        return GetTransactionUseCase(transactionRepository)
    }
}