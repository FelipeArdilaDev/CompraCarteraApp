package com.example.pruebatecnicaandroid.domain.repository

import com.example.pruebatecnicaandroid.data.network.ApiService
import com.example.pruebatecnicaandroid.data.repository.CardRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CardRepositoryModule {

    @Provides
    @Singleton
    fun provideCardRepository(apiService: ApiService): CardRepository {
        return CardRepositoryImpl(apiService)
    }
}