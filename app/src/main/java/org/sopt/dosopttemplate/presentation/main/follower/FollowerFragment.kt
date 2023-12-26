package org.sopt.dosopttemplate.presentation.main.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.data.model.response.FollowerResponse
import org.sopt.dosopttemplate.databinding.FragmentFollowerListBinding
import org.sopt.dosopttemplate.di.ServicePool
import org.sopt.dosopttemplate.util.shortToast
import retrofit2.Call
import retrofit2.Response

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerListBinding? = null
    private val binding : FragmentFollowerListBinding get() = requireNotNull(_binding)

    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        ServicePool.followerService.getFollowerList(2)
            .enqueue(object : retrofit2.Callback<FollowerResponse> {
                override fun onResponse(
                    call: Call<FollowerResponse>,
                    response: Response<FollowerResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data

                        setFollowerList(data ?: return)
                    }
                }

                override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                    requireContext().shortToast("서버 에러 발생")
                }
            })
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter(requireContext())
        binding.recyclerView.adapter = followerAdapter
    }

    private fun setFollowerList(followerData: List<FollowerResponse.FollowerData>) {
        followerAdapter.setFollowerList(followerData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
