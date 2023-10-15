package org.sopt.dosopttemplate

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent(this, MainActivity::class.java)
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
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }else{
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLoginSignup.setOnClickListener{
            val intentSignUpAcitivty = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intentSignUpAcitivty)
        }


    }
}