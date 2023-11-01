package org.sopt.dosopttemplate.presentation.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.HomeActivity
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.shortToast


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent(this, HomeActivity::class.java)
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val id = result.data?.getStringExtra("id") ?: ""
                val password = result.data?.getStringExtra("password") ?: ""
                val nickname = result.data?.getStringExtra("nickname") ?: ""
                val mbti = result.data?.getStringExtra("mbti") ?: ""

                intent.putExtra("id" ,id)
                    .putExtra("password", password)
                    .putExtra("nickname", nickname)
                    .putExtra("mbti", mbti)
            }
        }

        binding.btnLoginLogin.setOnClickListener{
            val enteredId = binding.etLoginId.text.toString()
            val enteredPwd = binding.etLoginPw.text.toString()

            if(enteredId.equals(intent.getStringExtra("id")) && enteredPwd.equals(intent.getStringExtra("password"))){
                shortToast("로그인 성공!")
                startActivity(intent)
            }else{
                shortToast("로그인 실패")
            }
        }

        binding.btnLoginSignup.setOnClickListener{
            val intentSignUpAcitivty = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intentSignUpAcitivty)
        }



    }

    //배경 터치하면 키보드 내려가게 하기
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }


}