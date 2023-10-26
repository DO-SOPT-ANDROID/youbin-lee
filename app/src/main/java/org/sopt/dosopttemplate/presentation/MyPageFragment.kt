package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
        private var _binding: FragmentMyPageBinding? = null
        private val binding get() = _binding!!
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

            val bundle = arguments
            binding.tvMyPageId.text = bundle?.getString("id")
            binding.tvMyPageMbti.text = bundle?.getString("mbti")
            binding.tvMyPageNickname.text = bundle?.getString("nickname")
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}