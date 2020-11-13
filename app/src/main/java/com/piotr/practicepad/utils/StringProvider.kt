package com.piotr.practicepad.utils

import android.content.Context
import androidx.annotation.StringRes

interface StringProvider {
    fun getStringForId(@StringRes stringRes: Int): String?
}

class AndroidStringProvider(private val context: Context) : StringProvider {

    companion object {
        fun initializeStringProvider(context: Context) {
            AndroidStringProvider(context)
        }
    }

    override fun getStringForId(stringRes: Int): String? {
        return context.getString(stringRes)
    }
}

