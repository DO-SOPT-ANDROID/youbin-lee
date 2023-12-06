package org.sopt.dosopttemplate.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.shortToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var user: User

    private val signUpViewModel: SignUpViewModel by viewModels { SignUpViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vm = signUpViewModel

        signUpBtnListener()
        observeSignUpSuccess()
    }

    private fun signUpBtnListener() {
        binding.btnSignUpSignup.setOnClickListener {
            user = signUpViewModel.createUser()
            signUpViewModel.checkSignUpAvailable(user, this)
        }
    }

    private fun observeSignUpSuccess() {
        signUpViewModel.signUpSuccess.observe(this) {
            if (it) {
                shortToast("회원가입 성공")
                intent.putExtra("User", user)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                shortToast("회원가입 실패")
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }
}