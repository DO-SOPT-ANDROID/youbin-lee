package org.sopt.dosopttemplate.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("id")&&intent.hasExtra("nickname")&&intent.hasExtra("mbti")){
            binding.tvMainId.text = intent.getStringExtra("id")
            binding.tvMainNickname.text = intent.getStringExtra("nickname")
            binding.tvMainMbti.text = intent.getStringExtra("mbti")
        }
    }
}