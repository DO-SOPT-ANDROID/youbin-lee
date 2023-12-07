package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.di.ServicePool

class LoginViewModelFactory : ViewModelProvider.Factory {

    // repository는 매개변수를 사용할 수 없음 그래서 create를 오버라이딩 해줌
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) { //modelClass가 LoginViewModel인지 확인
            val repository =
                LoginRepository(ServicePool.authService)// authService를 인자로 받아서 Repository인스턴스 생성
            return LoginViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}