package org.sopt.dosopttemplate.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    val password: String,
    val nickName: String,
    val mbti: String
) : Parcelable