package org.sopt.dosopttemplate.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.presentation.auth.LoginActivity

class MyPageFragment : Fragment() {
        private var _binding: FragmentMyPageBinding? = null
        private val binding get() = _binding!!
        private lateinit var user : User
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentMyPageBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

//            getUserData()

            binding.run{
                tvMyPageId.text = user.id
                tvMyPageNickname.text = user.nickName
                tvMyPageMbti.text = user.mbti
            }

            logoutBtnListener()

        }
//    private fun getUserData() {
//        user = arguments?.getParcelable("User") ?: User()
//    }
        private fun logoutBtnListener(){
            binding.btnMyPageLogout.setOnClickListener{
                val intent = Intent(getActivity(), LoginActivity::class.java)
                startActivity(intent)
             }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}