package org.sopt.dosopttemplate.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.datepicker.MaterialDatePicker
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.presentation.auth.LoginActivity

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding : FragmentMyPageBinding get () = requireNotNull(_binding)

    private lateinit var user: User
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

        getUserData()

        binding.run {
            tvMyPageId.text = user.id
            tvMyPageNickname.text = user.nickName
            tvMyPageMbti.text = user.mbti
        }

        datePickerListener()
        logoutBtnListener()

    }

    private fun getUserData() {
        user = arguments?.getParcelable("User")!!
    }

    private fun logoutBtnListener() {
        binding.btnMyPageLogout.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }

    private fun datePickerListener() {
        binding.btnMyPageBirth.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            datePicker.addOnPositiveButtonClickListener { selection ->
                binding.tvMyPageBirth.text = datePicker.headerText
            }
            datePicker.show(childFragmentManager, datePicker.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}