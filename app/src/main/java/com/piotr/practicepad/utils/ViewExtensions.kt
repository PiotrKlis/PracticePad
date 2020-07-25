package com.piotr.practicepad.utils

import android.view.View

inline var View.isNotVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }
