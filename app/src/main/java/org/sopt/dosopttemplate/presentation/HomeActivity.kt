package org.sopt.dosopttemplate.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickBottomNavigation()
    }
    private fun clickBottomNavigation(){
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu_do_android -> {
                    replaceFragment(DoAndroidFragment())
                    true
                }
                R.id.menu_mypage -> {
                    val mypagefragment : MyPageFragment = MyPageFragment()
                    val bundle : Bundle = Bundle()
                    bundle.putString("id", intent.getStringExtra("id"))
                    bundle.putString("password", intent.getStringExtra("password"))
                    bundle.putString("nickname", intent.getStringExtra("nickname"))
                    bundle.putString("mbti", intent.getStringExtra("mbti"))

                    mypagefragment.arguments = bundle

                    replaceFragment(mypagefragment)
                    true
                }
                else -> false
            }
        }
    }
    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }
}