package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.data.repository.AuthRepository
import org.sopt.dosopttemplate.di.ServicePool

class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) { //modelClass가 AuthViewModel인지 확인
            val repository =
                AuthRepository(ServicePool.authService)// authService를 인자로 받아서 Repository인스턴스 생성
            return AuthViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}