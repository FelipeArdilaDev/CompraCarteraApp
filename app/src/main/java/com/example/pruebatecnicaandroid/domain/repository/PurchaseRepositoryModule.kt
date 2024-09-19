package com.example.pruebatecnicaandroid.domain.repository

import com.example.pruebatecnicaandroid.domain.usecase.ProcessPurchaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PurchaseRepositoryModule {

    @Provides
    @Singleton
    fun providePurchaseRepository(): PurchaseRepository {
        return PurchaseRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideProcessPurchaseUseCase(purchaseRepository: PurchaseRepository): ProcessPurchaseUseCase {
        return ProcessPurchaseUseCase(purchaseRepository)
    }
}