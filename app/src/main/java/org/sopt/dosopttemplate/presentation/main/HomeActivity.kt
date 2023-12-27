package org.sopt.dosopttemplate.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import doubleBackPressed
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.presentation.main.doandroid.DoAndroidFragment
import org.sopt.dosopttemplate.presentation.main.follower.FollowerFragment
import org.sopt.dosopttemplate.presentation.main.home.HomeFragment
import org.sopt.dosopttemplate.presentation.main.mypage.MyPageFragment
import org.sopt.dosopttemplate.util.getParcelable

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doubleBackPressed()
        clickBottomNavigation()
        getUserData()
    }

    private fun clickBottomNavigation() {
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

                R.id.menu_follower_list -> {
                    replaceFragment(FollowerFragment())
                    true
                }

                R.id.menu_mypage -> {
                    val myPageFragment = MyPageFragment().apply {
                        arguments = Bundle().apply {
                            putParcelable("User", user)
                        }
                    }
                    replaceFragment(myPageFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

    private fun getUserData() {
        user = intent.getParcelable("User", User::class.java)!!
    }
}