package org.sopt.dosopttemplate.data

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R

class HomeViewModel : ViewModel() {

    val mockFriend = listOf<Friend>(
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "이유빈임당~",
            self_description = "꼼짝마 !!!!"
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name ="손흥민민",
            self_description = "이것이 축구다"
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name ="파트장장",
            self_description = "표정 푸렁ㄱ"
        )
    )
}