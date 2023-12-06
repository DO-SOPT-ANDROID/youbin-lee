package org.sopt.dosopttemplate.data.mock

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.presentation.main.home.FriendSealed

class MockFriendData : ViewModel() {

    val mockFriend = listOf<FriendSealed>(
        FriendSealed.MyProfile(
            profileImage = R.drawable.iv_my_profile,
            name = "이유빈"
        ),
        FriendSealed.FriendMusic(
            profileImage = R.drawable.iv_friend_profile1,
            name = "SOPT OB 손흥민",
            self_description = "이것이 축구다",
            music = "Hype Boy - 뉴진스"
        ),
        FriendSealed.FriendProfile(
            profileImage = R.drawable.iv_friend_profile2,
            name = "SOPT OB 이강인",
            self_description = null
        ),
        FriendSealed.FriendMusic(
            profileImage = R.drawable.iv_friend_profile3,
            name = "SOPT OB 정우영",
            self_description = null,
            music = "Haru Haru - 빅뱅"
        ),
        FriendSealed.FriendMusic(
            profileImage = R.drawable.iv_friend_profile4,
            name = "SOPT YB 해리케인",
            self_description = "줄게 내 Galaxy,,",
            music = "우주를 줄게 - 볼사"
        ),
        FriendSealed.FriendProfile(
            profileImage = R.drawable.iv_friend_profile5,
            name = "SOPT YB 살라",
            self_description = "일주일 동안 연락 안 돼요"
        ),
        FriendSealed.FriendMusic(
            profileImage = R.drawable.iv_friend_profile6,
            name = "SOPT YB 엘링 홀란",
            self_description = "뉴진스의 하입보이요",
            music = "You & Me - JENNIE"
        ),
        FriendSealed.FriendProfile(
            profileImage = R.drawable.iv_friend_profile7,
            name = "SOPT YB 백승호",
            self_description = null
        ),
        FriendSealed.FriendMusic(
            profileImage = R.drawable.iv_friend_profile8,
            name = "SOPT YB 히샬리송",
            self_description = "통그라미!!",
            music = "Bubble - STAYC"
        )
    )
}