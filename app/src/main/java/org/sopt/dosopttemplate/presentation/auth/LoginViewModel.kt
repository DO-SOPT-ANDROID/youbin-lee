package org.sopt.dosopttemplate.presentation.auth

// 보시면 알겠지만 LiveData는 lifecycle 라이브러리에 들어있습니다!
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.model.response.ResponseLoginDto
import org.sopt.dosopttemplate.data.repository.AuthRepository
import org.sopt.dosopttemplate.di.ServicePool.authService
// 서비스 객체를 따로 만들지 않고 바로 불러왔습니다.
// 언제나 그렇듯 Call과 Callback은 Retrofit2의 것을 사용해야 합니다. okhttp 아님 주의!

class LoginViewModel(repository: AuthRepository) : ViewModel() {
    // MutableLiveData를 사용하여 login result 객체를 생성합니다.
    val loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    //mutable은 value로 접근

    fun login() {
        viewModelScope.launch {
            runCatching {
                authService.checkLoginAvailableFromServer(
                    RequestLoginDto(
                        id.value,
                        pw.value
                    )
                )
            }.onSuccess {
                loginResult.value = it
                loginSuccess.value = true
            }.onFailure {
                loginSuccess.value = false
            }
        }
    }
}