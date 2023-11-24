package org.sopt.dosopttemplate.presentation.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.model.response.ResponseLoginDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//
//class LoginViewModel : ViewModel() {
//    var user: User? = null
//
//    val enteredId = MutableLiveData<String>()
//    val enteredPwd = MutableLiveData<String>()
//    val loginResult = MutableLiveData<String>()
//
//    fun updateUser(userInfo: User) {
//        user = userInfo
//    }
//
//    fun checkLoginInfo(): Boolean {
//        return enteredId.value.equals(user?.id) && enteredPwd.value.equals(user?.password)
//    }
//
//    fun login(){
//        Log.d("LYB", "뷰모델 들어옴")
//        // 로그인 메소드에 맞는 파라미터 담아주기
//        authService.login(RequestLoginDto(enteredId.value!!, enteredPwd.value!!))
//            // enqueue를 통해 비동기 방식으로 네트워크 요청 보내기
//            .enqueue(object : Callback<ResponseLoginDto> {
//                // 요청이 성공한 경우 호출
//                override fun onResponse(
//                    call: Call<ResponseLoginDto>,
//                    response: Response<ResponseLoginDto>,
//                ) {
//                    if (response.isSuccessful) {
//                        val data: ResponseLoginDto = response.body()!!
//                        val userId = data.id
//                        loginResult.value = "로그인이 성공하였고 유저의 ID는 $userId 입니다."
//                    }
//                }
//                // 요청 중 오류 발생한 경우 호출
//                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
//                    loginResult.value = "서버 에러 발생"
//                }
//            })
//    }
//}
