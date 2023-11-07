package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.shortToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpBtnListener()

    }
    private fun signUpBtnListener(){
        binding.btnSignUpSignup.setOnClickListener{
            val id = binding.etSignUpId.text.toString()
            val password = binding.etSignUpPw.text.toString()
            val nickname = binding.etSignUpNickname.text.toString()
            val mbti = binding.etSignUpMbti.text.toString()
            var check : Boolean = true

            if(id.isEmpty() || id.length < 6 || id.length > 10) check = false
            if(password.isEmpty() || password.length < 8 || password.length > 12) check = false
            if(nickname.isEmpty()) check = false
            if(mbti.isEmpty()) check = false

            if(check){
                shortToast("회원가입 성공!")

                // User에 객체 전달
                user = User(id, password, nickname, mbti)
                intent.putExtra("User", user)

                setResult(RESULT_OK, intent)
                finish()
            }else{
                shortToast("정보를 다시 입력해주세요")
            }
        }
    }
    //배경 터치하면 키보드 내려가게 하기
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

}