package org.sopt.dosopttemplate.data

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R

class HomeViewModel : ViewModel() {

    val mockFriend = listOf<Friend>(
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "이유빈임당~",
            self_description = "안드 뿌시기"
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name ="손흥민",
            self_description = "이것이 축구다"
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name ="이강인",
            self_description = "파이팅"
        )
    )
}