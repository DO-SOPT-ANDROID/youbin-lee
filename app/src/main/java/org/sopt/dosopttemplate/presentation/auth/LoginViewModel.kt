package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.data.model.request.LoginRequestDto
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.util.UiState

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<UiState<User>>(UiState.Loading)
    val loginState: StateFlow<UiState<User>> get() = _loginState.asStateFlow()

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
                    _loginState.value = UiState.Success(
                        User(
                            it.id.toString(),
                            password = "",
                            it.nickname,
                            mbti = ""
                        )
                    )
                }.onFailure {
                    _loginState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}