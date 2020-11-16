package com.piotr.practicepad.extensions

import android.view.LayoutInflater
import android.view.View

fun View.getLayoutInflater(): LayoutInflater = LayoutInflater.from(context)
inline var View.isNotVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }
