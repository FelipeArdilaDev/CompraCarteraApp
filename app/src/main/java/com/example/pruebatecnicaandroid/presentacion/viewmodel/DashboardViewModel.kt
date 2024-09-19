package com.example.pruebatecnicaandroid.presentacion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebatecnicaandroid.domain.model.UserInfo
import com.example.pruebatecnicaandroid.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val getUserInfoUseCase: GetUserInfoUseCase) :
    ViewModel() {

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo> get() = _userInfo

    fun loadUserInfo() {
        _userInfo.value = getUserInfoUseCase.invoke()
    }
}