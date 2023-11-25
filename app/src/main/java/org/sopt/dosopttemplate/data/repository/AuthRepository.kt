package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.service.AuthService

class AuthRepository(
    private val authService: AuthService
) {
    // runCatching 이용해서 하는 거 기억하기
    suspend fun getLogin(user: RequestLoginDto) =
        runCatching {
            authService.checkLoginAvailableFromServer(user)
        }
}