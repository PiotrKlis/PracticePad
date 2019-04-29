package com.piotr.practicepad.ui.main.utils

import android.content.Context
import androidx.annotation.StringRes

interface StringProvider {
    fun getStringForId(@StringRes stringRes: Int): String?
}

class AndroidStringProvider(var _context: Context) : StringProvider {

    companion object {
        fun initializeStringProvider(context: Context) {
            AndroidStringProvider(context)
        }
    }

    var context: Context? = null

    init {
        this.context = _context
    }

    override fun getStringForId(stringRes: Int): String? {
        return context?.getString(stringRes)
    }
}

