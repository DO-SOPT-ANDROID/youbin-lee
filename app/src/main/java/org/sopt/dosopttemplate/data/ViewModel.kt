package org.sopt.dosopttemplate.data

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R

class ViewModel : ViewModel() {

    val mockFriend = listOf<FriendSealed>(
        FriendSealed.MyProfile(
            profileImage = R.drawable.iv_my_profile,
            name = "이유빈",
        ),
        FriendSealed.FriendProfile(
            profileImage = R.drawable.iv_friend_profile_son,
            name ="SOPT OB 손흥민",
            self_description = "이것이 축구다"
        ),
        FriendSealed.FriendProfile(
            profileImage = R.drawable.iv_profile,
            name ="이강인",
            self_description = "파이팅"
        )
    )
}