package com.piotr.practicepad.utils

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface StringProvider {
    fun getStringForId(@StringRes stringRes: Int): String
}

class AndroidStringProvider @Inject constructor(private val context: Context) : StringProvider {
    override fun getStringForId(stringRes: Int): String = context.getString(stringRes)
}
