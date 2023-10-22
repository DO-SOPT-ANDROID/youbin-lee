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
            profileImage = R.drawable.iv_friend_profile1,
            name ="SOPT OB 손흥민",
            self_description = "이것이 축구다"
        ),
        FriendSealed.FriendProfile(
            profileImage = R.drawable.iv_friend_profile2,
            name ="SOPT OB 이강인",
            self_description = "배고파"
        ),
        FriendSealed.FriendMusic(
            profileImage = R.drawable.iv_friend_profile3,
            name ="SOPT YB 정우영",
            self_description = "배고파",
            music = "Haru Haru - 빅뱅",
        )
    )
}