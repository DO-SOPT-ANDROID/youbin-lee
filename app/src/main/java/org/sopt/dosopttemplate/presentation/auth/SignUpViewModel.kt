package org.sopt.dosopttemplate.presentation.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
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
        Log.d("LYB", "일단 함수는 들어옴")

        if (isInputValid(signUpId.value, signUpPw.value, signUpNickname.value, signUpMbti.value)) {
            Log.d("LYB", "유효한 정보임")
            viewModelScope.launch {
                repository.getSignUp(
                    RequestSignUpDto(
                        signUpId.value!!,
                        signUpPw.value!!,
                        signUpNickname.value!!
                    )
                )
                    .onSuccess {
                        Log.d("LYB", "성공으로 들어옴")
                        _signUpResult.value = it
                        _signUpSuccess.value = true
                    }
                    .onFailure {
                        _signUpSuccess.value = false
                    }
            }
        } else {
            Log.d("LYB", "조건에 충족하지 않음")

        }
    }

    private fun isInputValid(
        id: String?,
        password: String?,
        nickname: String?,
        mbti: String?
    ): Boolean {
        Log.d("LYB", "유효한지 검사하는 중")
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