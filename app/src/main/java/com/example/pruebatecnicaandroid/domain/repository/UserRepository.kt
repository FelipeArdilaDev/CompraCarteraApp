package com.example.pruebatecnicaandroid.domain.repository

import com.example.pruebatecnicaandroid.domain.model.UserInfo

interface UserRepository {
    fun getUserInfo(): UserInfo
}