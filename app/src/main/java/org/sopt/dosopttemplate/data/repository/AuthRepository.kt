package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.service.AuthService

class AuthRepository(
    private val authService: AuthService
) {
    suspend fun getLogin(user: RequestLoginDto) =
        runCatching {
            authService.login(user)
        }
}