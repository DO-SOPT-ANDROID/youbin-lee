package org.sopt.dosopttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpSignupBtn.setOnClickListener{
            val id = binding.signUpIdEt.text.toString()
            val password = binding.signUpPwEt.text.toString()
            val nickname = binding.signUpNicknameEt.text.toString()
            val mbti = binding.signUpMbtiEt.text.toString()
            var check : Boolean = true

            if(id.isEmpty() || id.length < 6 || id.length > 10) check = false
            if(password.isEmpty() || password.length < 8 || password.length > 12) check = false
            if(nickname.isEmpty()) check = false
            if(mbti.isEmpty()) check = false

            if(check){
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("id", id)
                    .putExtra("password", password)
                    .putExtra("nickname", nickname)
                    .putExtra("mbti", mbti)
                setResult(RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this, "정보를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    }

}