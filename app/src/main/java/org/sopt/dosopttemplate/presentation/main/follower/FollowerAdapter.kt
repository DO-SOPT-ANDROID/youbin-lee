package org.sopt.dosopttemplate.presentation.main.follower

import org.sopt.dosopttemplate.data.model.response.FollowerResponse

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemFollowerBinding

class FollowerAdapter(context: Context) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var followerList = mutableListOf<FollowerResponse.FollowerData>()

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(followerData: FollowerResponse.FollowerData) {
            binding.run {
                tvFollowerName.text = followerData.first_name
                tvFollowerEmail.text = followerData.email
                imgFollowerProfile.load(followerData.avatar) {
                    placeholder(R.drawable.iv_my_profile)
                    error(R.drawable.iv_my_profile)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(ItemFollowerBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = followerList.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    fun setFollowerList(followerData: List<FollowerResponse.FollowerData>) {
        followerList.clear()
        followerList.addAll(followerData)
        notifyDataSetChanged()
    }
}