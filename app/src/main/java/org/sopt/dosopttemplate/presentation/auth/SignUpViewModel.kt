package org.sopt.dosopttemplate.presentation.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.data.model.request.SignUpRequestDto
import org.sopt.dosopttemplate.data.repository.SignUpRepository
import java.util.regex.Pattern


class SignUpViewModel(private val repository: SignUpRepository) : ViewModel() {
    private val _signUpResult: MutableLiveData<Unit> = MutableLiveData()
    val signUpResult: LiveData<Unit> get() = _signUpResult
    private val _signUpSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signUpSuccess: LiveData<Boolean> get() = _signUpSuccess

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


    fun createUser(): User {
        return User(signUpId.value!!, signUpPw.value!!, signUpNickname.value!!, signUpMbti.value!!)
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