package org.sopt.dosopttemplate.util

import android.content.Intent
import android.os.Build

// Intent에 저장된 Parcelable 데이터 가져오기

fun <T> Intent.getParcelable(name: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, clazz)
    } else {
        getParcelableExtra(name)
    }
}