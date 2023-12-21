package org.sopt.dosopttemplate.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.shortToast
import kotlin.math.sign

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var user: User

    private val signUpViewModel: SignUpViewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        binding.vm = signUpViewModel

        signUpBtnListener()
        observeSignUpSuccess()
        observeSignUpFormat()

    }

    private fun signUpBtnListener() {
        binding.btnSignUpSignup.setOnClickListener {
            user = signUpViewModel.createUser()
            signUpViewModel.checkSignUpAvailable(user, this)
        }
    }

    private fun observeSignUpSuccess() {
        lifecycleScope.launch {
            signUpViewModel.signUpState.flowWithLifecycle(lifecycle).onEach {signUpState ->
                when(signUpState){
                    is UiState.Success -> {
                        shortToast("회원가입 성공")
                        goToLoginActivity()
                    }
                    is UiState.Failure -> {
                        shortToast("회원가입 실패: ${signUpState.msg}")
                    }
                    is UiState.Loading -> {
                        shortToast(getString(R.string.ui_state_loading))
                    }
                }
            }.launchIn(lifecycleScope)
        }
    }

    private fun observeSignUpFormat() {
        signUpViewModel.isIdValid.observe(this) { isValid ->
            if (!isValid) {
                binding.layoutSignUpId.isErrorEnabled = true
                binding.layoutSignUpId.error = getString(R.string.sign_up_id_error)
            } else {
                binding.layoutSignUpId.isErrorEnabled = false
            }
            signUpViewModel.checkButton()
        }
        signUpViewModel.isPwValid.observe(this) { isValid ->
            if (!isValid) {
                binding.layoutSignUpPw.isErrorEnabled = true
                binding.layoutSignUpPw.error = getString(R.string.sign_up_pw_error)
            } else {
                binding.layoutSignUpPw.isErrorEnabled = false
            }
            signUpViewModel.checkButton()
        }
    }

    private fun goToLoginActivity(){
        intent.putExtra("User", user)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }
}