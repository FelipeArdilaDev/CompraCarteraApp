package com.example.pruebatecnicaandroid.data.repository

import com.example.pruebatecnicaandroid.domain.model.UserInfo
import com.example.pruebatecnicaandroid.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun getUserInfo(): UserInfo {
        return UserInfo(
            name = "Felipe",
            balance = 425785,
            cardNumber = "***3456"
        )
    }
}