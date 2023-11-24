package org.sopt.dosopttemplate.data.service

import retrofit2.Call
import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/v1/members")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<Unit>

    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}