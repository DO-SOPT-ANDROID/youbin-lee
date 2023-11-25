package org.sopt.dosopttemplate.presentation.auth

// 보시면 알겠지만 LiveData는 lifecycle 라이브러리에 들어있습니다!
import androidx.lifecycle.LiveData
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

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {
    // MutableLiveData를 사용하여 login result 객체를 생성합니다.
    private val _loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginResult: LiveData<ResponseLoginDto>
        get() =  _loginResult

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: LiveData<Boolean>
        get() =  _loginSuccess

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    //mutable은 value로 접근

    fun login() {
        // 근데 이제 ViewModel이 모든 일을 처리하지 않고 역할 분담을 함
        // repository에서 runcatching을 해줬으니까 여기서는 해줄 필요가 없음
        viewModelScope.launch {
            // repository로 일을 분리시킴
            repository.getLogin(RequestLoginDto(
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