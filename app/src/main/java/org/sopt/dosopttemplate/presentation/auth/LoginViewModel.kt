package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.request.LoginRequestDto
import org.sopt.dosopttemplate.data.model.response.LoginResponseDto
import org.sopt.dosopttemplate.data.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _loginResult: MutableLiveData<LoginResponseDto> = MutableLiveData()
    val loginResult: LiveData<LoginResponseDto> get() = _loginResult

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    fun checkLoginAvailable() {
        viewModelScope.launch {
            repository.getLogin(
                LoginRequestDto(
                    id.value,
                    pw.value
                )
            )
                .onSuccess {
                    _loginResult.value = it
                    _loginSuccess.value = true
                }.onFailure {
                    _loginSuccess.value = false
                }
        }
    }
}