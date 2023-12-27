package org.sopt.dosopttemplate.presentation.main.follower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.response.FollowerResponseDto
import org.sopt.dosopttemplate.databinding.FragmentFollowerBinding
import org.sopt.dosopttemplate.presentation.ViewModelFactory
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.shortToast

@AndroidEntryPoint
class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding: FragmentFollowerBinding get() = requireNotNull(_binding)
    private lateinit var followerAdapter: FollowerAdapter
    val followerViewModel: FollowerViewModel by viewModels { ViewModelFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initFollowerList()
        observeFollowerState()

    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter(requireContext())
        binding.recyclerView.adapter = followerAdapter
    }

    private fun initFollowerList() {
        followerViewModel.getFollowerListFromServer(2)
    }

    private fun observeFollowerState() {
        followerViewModel.followerState.flowWithLifecycle(lifecycle).onEach { followerState ->
            when (followerState) {
                is UiState.Success -> {
                    val followerData = followerState.data
                    setFollowerList(followerData)
                }

                is UiState.Failure -> {
                    shortToast("팔로워 통신 실패: ${followerState.msg}")
                }

                is UiState.Loading -> {
                    shortToast(getString(R.string.ui_state_loading))
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setFollowerList(followerData: List<FollowerResponseDto.FollowerData>) {
        followerAdapter.setFollowerList(followerData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
