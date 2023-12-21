package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.request.LoginRequestDto
import org.sopt.dosopttemplate.data.service.AuthService

class LoginRepository(private val authService: AuthService) {
    suspend fun getLogin(user: LoginRequestDto) =
        runCatching {
            authService.checkLoginAvailableFromServer(user)
        }
}