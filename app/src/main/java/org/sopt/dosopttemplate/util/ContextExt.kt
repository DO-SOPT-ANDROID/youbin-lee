package org.sopt.dosopttemplate.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import android.view.inputmethod.InputMethodManager

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus ?: View(this)
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
