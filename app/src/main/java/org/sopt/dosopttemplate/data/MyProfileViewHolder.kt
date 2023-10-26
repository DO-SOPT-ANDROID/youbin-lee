package org.sopt.dosopttemplate.data

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding

class MyProfileViewHolder(private val binding: ItemMyProfileBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData : FriendSealed.MyProfile){
        binding.ivProfile.setImageResource(friendData.profileImage)
        binding.tvName.text = friendData.name
    }
}