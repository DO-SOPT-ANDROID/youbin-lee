package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.request.SignUpRequestDto
import org.sopt.dosopttemplate.data.service.AuthService

class SignUpRepository(private val authService: AuthService) {
suspend fun getSignUp(user: SignUpRequestDto) =
    runCatching {
        authService.checkSignUpAvailableFromServer(user)
    }
}