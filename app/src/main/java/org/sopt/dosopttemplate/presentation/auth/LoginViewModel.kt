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
    //mutable은 value로 접근

    fun checkLoginAvailable() {
        // 근데 이제 ViewModel이 모든 일을 처리하지 않고 역할 분담을 함
        // repository에서 runcatching을 해줬으니까 여기서는 해줄 필요가 없음
        viewModelScope.launch {
            // repository로 일을 분리시킴
            repository.getLogin(
                LoginRequestDto(
                    id.value,
                    pw.value
                )
            )
                // 그러면 ViewModel이 맞았는지 확인만 함
                .onSuccess {
                    _loginResult.value = it
                    _loginSuccess.value = true
                }.onFailure {
                    _loginSuccess.value = false
                }
        }
    }
}