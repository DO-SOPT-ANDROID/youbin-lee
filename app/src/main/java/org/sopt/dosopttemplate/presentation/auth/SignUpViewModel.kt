package org.sopt.dosopttemplate.presentation.auth

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.data.model.request.SignUpRequestDto
import org.sopt.dosopttemplate.di.ServicePool.authService
import org.sopt.dosopttemplate.util.UiState
import java.util.regex.Pattern


class SignUpViewModel() : ViewModel() {

    private val _signUpState = MutableStateFlow<UiState<User>>(UiState.Loading)
    val signUpState : StateFlow<UiState<User>> get() = _signUpState

    val signUpId = MutableLiveData<String>()
    val signUpPw = MutableLiveData<String>()
    val signUpNickname = MutableLiveData<String>()
    val signUpMbti = MutableLiveData<String>()

    val isIdValid = signUpId.map { checkId(it) }
    val isPwValid = signUpPw.map { checkPw(it) }

    val isButtonValid: MutableLiveData<Boolean> = MutableLiveData(false)
    fun checkButton() {
        isButtonValid.value = (isIdValid.value == true && isPwValid.value == true)
    }

    fun checkSignUpAvailable(signUpUser: User, context: Context) {
        viewModelScope.launch {
            runCatching {
                authService.checkSignUpAvailableFromServer(
                    SignUpRequestDto(
                        signUpId.value ?: "",
                        signUpPw.value ?: "",
                        signUpNickname.value ?: ""
                    )
                )
            }
                .onSuccess {
                    _signUpState.value = UiState.Success(User(signUpId.toString(), signUpPw.toString(), signUpNickname.toString(), signUpMbti.toString()))
                }
                .onFailure {
                    _signUpState.value = UiState.Failure(it.message.toString())
                }
        }
    }


    fun createUser(): User {
        return User(
            signUpId.value ?: "",
            signUpPw.value ?: "",
            signUpNickname.value ?: "",
            signUpMbti.value ?: ""
        )
    }

    fun checkId(id: String?): Boolean {
        val idMatcher = ID_PATTERN.matcher(id.orEmpty())
        return id.isNullOrBlank() || idMatcher.find()
    }

    fun checkPw(password: String?): Boolean {
        val passwordMatcher = PW_PATTERN.matcher(password.orEmpty())
        return password.isNullOrBlank() || passwordMatcher.find()
    }

    companion object {
        const val ID_REGEX = """^(?=.*[a-zA-Z])(?=.*\d).{6,10}$"""
        val ID_PATTERN: Pattern = Pattern.compile(ID_REGEX)
        const val PW_REGEX = """^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()?]).{6,12}$"""
        val PW_PATTERN: Pattern = Pattern.compile(PW_REGEX)
    }
}