package org.sopt.dosopttemplate.domain.entity

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    val password: String,
    val nickName: String,
    val mbti: String
) : Parcelable
