package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.service.AuthService

class SignUpRepository(private val authService: AuthService) {
suspend fun getSignUp(user: RequestSignUpDto) =
    runCatching {
        authService.checkSignUpAvailableFromServer(user)
    }
}