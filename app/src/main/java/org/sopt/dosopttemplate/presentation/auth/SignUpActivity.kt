package org.sopt.dosopttemplate.presentation.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.viewModels
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.di.ServicePool
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.shortToast
import retrofit2.Call
import retrofit2.Response

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
            signUpViewModel.checkSignUpAvailable()
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