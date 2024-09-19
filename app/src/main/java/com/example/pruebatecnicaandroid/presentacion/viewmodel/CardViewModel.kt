package com.example.pruebatecnicaandroid.presentacion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicaandroid.data.model.BinResponse
import com.example.pruebatecnicaandroid.domain.usecase.GetCardUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCardInfoUseCase: GetCardUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _binInfo = MutableLiveData<BinResponse?>()
    val binInfo: LiveData<BinResponse?> = _binInfo

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun validateBinAndProceed(bin: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = getCardInfoUseCase(bin)
            _isLoading.value = false
            if (response != null && response.success) {
                Log.e("TAG", "validateBinAndProceed: ${Gson().toJson(response)}")
                _binInfo.postValue(response)

            } else {
                Log.e("TAG", "validateBinAndProceed: exception")
                _errorMessage.postValue(response.toString())
            }
        }
    }
}
