package org.sopt.dosopttemplate.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.FriendSealed
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemFriendMusicBinding
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding

class FriendAdapter(context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy{ LayoutInflater.from(context)}
    private var friendList : List<FriendSealed> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.item_friend -> FriendProfileViewHolder(
                ItemFriendBinding.bind(view),
            )
            R.layout.item_friend_music -> FriendMusicViewHolder(
                ItemFriendMusicBinding.bind(view),
            )

            else -> MyProfileViewHolder(
                ItemMyProfileBinding.bind(view),
            )
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = friendList[position]

        when (holder) {
            is FriendProfileViewHolder -> holder.onBind(item as FriendSealed.FriendProfile)
            is MyProfileViewHolder -> holder.onBind(item as FriendSealed.MyProfile)
            is FriendMusicViewHolder -> holder.onBind(item as FriendSealed.FriendMusic)
        }
    }

    override fun getItemCount()= friendList.size

    // 임시 리스트에 준비해둔 가짜 리스트를 연결하는 함수
    fun setFriendList(friendList : List<FriendSealed>){
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (friendList[position]) {
        is FriendSealed.MyProfile -> R.layout.item_my_profile
        is FriendSealed.FriendProfile -> R.layout.item_friend
        is FriendSealed.FriendMusic -> R.layout.item_friend_music
    }

}