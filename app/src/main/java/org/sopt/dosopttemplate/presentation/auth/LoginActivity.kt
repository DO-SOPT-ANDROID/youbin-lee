package org.sopt.dosopttemplate.presentation.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.main.HomeActivity
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.shortToast


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUpActivityLauncher()
        initLoginBtnListener()
        initSignUpBtnListener()

    }
    private fun initSignUpActivityLauncher(){
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                user = result.data?.getParcelable("User", User::class.java)!!
            }
        }
    }

    private fun initLoginBtnListener(){
        binding.btnLoginLogin.setOnClickListener{
            val enteredId = binding.etLoginId.text.toString()
            val enteredPwd = binding.etLoginPw.text.toString()

            var intent = Intent(this, HomeActivity::class.java)

            if(enteredId.equals(user.id) && enteredPwd.equals(user.password)){
                shortToast("로그인 성공!")
                intent.putExtra("User", user)
                startActivity(intent)
            }else{
                shortToast("로그인 실패")
            }
        }
    }

    private fun initSignUpBtnListener(){
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