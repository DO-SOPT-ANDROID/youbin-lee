package org.sopt.dosopttemplate.presentation.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.main.HomeActivity
import org.sopt.dosopttemplate.util.UiState
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
        lifecycleScope.launch {
            loginViewModel.loginState.collect { loginState ->
                when (loginState) {
                    is UiState.Success -> {
                        val userId = loginState.data.id
                        shortToast("로그인이 성공하였고 유저의 ID는 $userId 입니둥")
                        goToHomeActivity()
                    }

                    is UiState.Failure -> {
                        shortToast("로그인 실패: ${loginState.msg}")
                    }

                    is UiState.Loading -> {
                        shortToast("로딩 중..")
                    }
                }
            }
        }
    }

    private fun goToHomeActivity() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.putExtra("User", user)
        startActivity(intent)
    }

    private fun initLoginBtnListener() {
        binding.btnLoginLogin.setOnClickListener {
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