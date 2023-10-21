package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.shortToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("id", id)
                    .putExtra("password", password)
                    .putExtra("nickname", nickname)
                    .putExtra("mbti", mbti)
                setResult(RESULT_OK, intent)
                finish()
            }else{
                shortToast("정보를 다시 입력해주세요")
            }
        }

    }

}