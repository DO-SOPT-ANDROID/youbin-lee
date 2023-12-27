package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.di.ServicePool

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                val repository = LoginRepository(ServicePool.authService)
                LoginViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}
