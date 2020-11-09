package com.piotr.practicepad.utils

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getResourceFor() 
}

interface StringProvider {
    fun getStringForId(@StringRes stringRes: Int): String?
}

class AndroidStringProvider(_context: Context) : StringProvider {

    companion object {
        fun initializeStringProvider(context: Context) {
            AndroidStringProvider(context)
        }
    }

    private var context: Context? = null

    init {
        this.context = _context
    }

    override fun getStringForId(stringRes: Int): String? {
        return context?.getString(stringRes)
    }
}

