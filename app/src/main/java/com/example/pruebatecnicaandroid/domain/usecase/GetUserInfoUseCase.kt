package com.example.pruebatecnicaandroid.domain.usecase

import com.example.pruebatecnicaandroid.domain.model.UserInfo
import com.example.pruebatecnicaandroid.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserInfoUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun invoke(): UserInfo {
        return userRepository.getUserInfo()
    }
}