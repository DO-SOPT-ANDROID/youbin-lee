package org.sopt.dosopttemplate.presentation.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.main.HomeActivity
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.setOnSingleClickListener
import org.sopt.dosopttemplate.util.shortToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var user: User? = null

    private val loginViewModel: LoginViewModel by viewModels { ViewModelFactory() }

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

    private fun initSignUpActivityLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                user = result.data?.getParcelable("User", User::class.java)
            }
        }
    }

    private fun initLoginBtnListener() {
        binding.btnLoginLogin.setOnClickListener {
            loginViewModel.checkLoginAvailable()
        }
    }

    private fun initSignUpBtnListener() {
        binding.btnLoginSignup.setOnSingleClickListener {
            val intentSignUpActivity = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intentSignUpActivity)
        }
    }

    private fun observeLoginSuccess() {
        lifecycleScope.launch {
            loginViewModel.loginState.flowWithLifecycle(lifecycle).onEach { loginState ->
                when (loginState) {
                    is UiState.Success -> {
                        val userId = loginState.data.id
                        shortToast(getString(R.string.ui_state_login_success, userId))
                        goToHomeActivity()
                    }

                    is UiState.Failure -> {
                        shortToast(getString(R.string.ui_state_login_failure, loginState.msg))
                    }

                    is UiState.Loading -> {
                        shortToast(getString(R.string.ui_state_loading))
                    }
                }
            }.launchIn(lifecycleScope)
        }
    }

    private fun goToHomeActivity() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.putExtra("User", user)
        startActivity(intent)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

}