package org.sopt.dosopttemplate.presentation.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.main.HomeActivity
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.shortToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var user: User

    private val loginViewModel: LoginViewModel by viewModels { LoginViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vm = loginViewModel

        initSignUpActivityLauncher()
        initLoginBtnListener()
        initSignUpBtnListener()
        observeLoginSuccess()
    }

    private fun observeLoginSuccess() {
        // onCreate 안에 observe 넣어줘야 함
        loginViewModel.loginSuccess.observe(this) {
            if (it) {
                val userId = loginViewModel.loginResult.value?.id
                shortToast("로그인이 성공하였고 유저의 ID는 $userId 입니둥")
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                intent.putExtra("User", user)
                startActivity(intent)
            } else {
                shortToast("로그인 실패")
            }
        }
    }

    private fun initLoginBtnListener() {
        binding.btnLoginLogin.setOnClickListener {
            // 액티비티가 ViewModel한테 일을 시킴
            loginViewModel.checkLoginAvailable()
        }
    }

    private fun initSignUpActivityLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                user = result.data?.getParcelable("User", User::class.java)!!
            }
        }
    }

    private fun initSignUpBtnListener() {
        binding.btnLoginSignup.setOnClickListener {
            val intentSignUpAcitivty = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intentSignUpAcitivty)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

}