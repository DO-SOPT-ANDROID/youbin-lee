package org.sopt.dosopttemplate.presentation.main.home.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendMusicBinding

class FriendMusicViewHolder (private val binding: ItemFriendMusicBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData : FriendSealed.FriendMusic){
        binding.ivProfile.setImageResource(friendData.profileImage)
        binding.tvName.text = friendData.name
        binding.tvSelfDescription.text = friendData.self_description
        binding.tvMusicTitle.text = friendData.music
    }
}