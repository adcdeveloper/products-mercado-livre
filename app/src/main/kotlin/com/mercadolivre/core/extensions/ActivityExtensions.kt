package com.mercadolivre.core.extensions

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(iBinder: IBinder): Boolean {
    val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return imm.hideSoftInputFromWindow(iBinder, 0)
}