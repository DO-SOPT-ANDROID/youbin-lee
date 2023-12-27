package org.sopt.dosopttemplate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.data.repository.FollowerRepositoryImpl
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.di.ServicePool
import org.sopt.dosopttemplate.presentation.auth.LoginViewModel
import org.sopt.dosopttemplate.presentation.main.follower.FollowerViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                val repository = LoginRepository(ServicePool.authService)
                LoginViewModel(repository) as T
            }
//            modelClass.isAssignableFrom(FollowerViewModel::class.java) -> {
//                val repository = FollowerRepositoryImpl(ServicePool.followerService)
//                FollowerViewModel(repository) as T
//            }
            else -> throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}
