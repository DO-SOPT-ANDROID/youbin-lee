package org.sopt.dosopttemplate.presentation.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.model.response.ResponseLoginDto
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.di.ServicePool.authService
import org.sopt.dosopttemplate.presentation.main.HomeActivity
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.shortToast
import retrofit2.Call
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var user : User

    private val authViewModel: AuthViewModel by viewModels { AuthViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vm = authViewModel
        initSignUpActivityLauncher()
        initLoginBtnListener()
        initSignUpBtnListener()

        // onCreate 안에 observe
        authViewModel.loginSuccess.observe(this){
            if(it){
            }else{

            }
        }

    }

    private fun  initLoginBtnListener() {
        binding.btnLoginLogin.setOnClickListener {

            authViewModel.login(
            )
        }
    }

    private fun initSignUpActivityLauncher(){
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                user = result.data?.getParcelable("User", User::class.java)!!
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
        currentFocus?.hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

}