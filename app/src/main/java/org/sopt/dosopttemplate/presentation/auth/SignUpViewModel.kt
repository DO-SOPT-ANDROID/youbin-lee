package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.request.SignUpRequestDto
import org.sopt.dosopttemplate.data.repository.SignUpRepository


class SignUpViewModel(private val repository: SignUpRepository) : ViewModel() {
    private val _signUpResult: MutableLiveData<Unit> = MutableLiveData()
    val signUpResult: LiveData<Unit> get() = _signUpResult
    private val _signUpSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signUpSuccess: LiveData<Boolean> get() = _signUpSuccess

    val signUpId = MutableLiveData<String>()
    val signUpPw = MutableLiveData<String>()
    val signUpNickname = MutableLiveData<String>()
    val signUpMbti = MutableLiveData<String>()
    fun checkSignUpAvailable() {
        if (isInputValid(signUpId.value, signUpPw.value, signUpNickname.value, signUpMbti.value)) {
            viewModelScope.launch {
                repository.getSignUp(
                    SignUpRequestDto(
                        signUpId.value!!,
                        signUpPw.value!!,
                        signUpNickname.value!!
                    )
                )
                    .onSuccess {
                        _signUpResult.value = it
                        _signUpSuccess.value = true
                    }
                    .onFailure {
                        _signUpSuccess.value = false
                    }
            }
        }
    }

    private fun isInputValid(
        id: String?,
        password: String?,
        nickname: String?,
        mbti: String?
    ): Boolean {
        if (id != null && password != null && nickname != null && mbti != null) {
            return id.isNotEmpty() && id.length in 6..10 &&
                    password.isNotEmpty() && password.length in 8..12 &&
                    nickname.isNotEmpty() &&
                    mbti.isNotEmpty()
        } else {
            return false
        }
    }

}