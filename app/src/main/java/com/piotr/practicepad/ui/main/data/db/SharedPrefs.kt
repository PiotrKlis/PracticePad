package com.piotr.practicepad.ui.main.data.db

import android.content.Context
import android.content.SharedPreferences

private const val KEY_ACTIVE_SET = "active_set"
private const val DEFAULT_SET: Int = 0

class SharedPrefs {

    companion object {
        private var prefs: SharedPreferences? = null

        fun initSharedPrefs(context: Context) {
            if (prefs == null) {
                prefs = context.getSharedPreferences(KEY_ACTIVE_SET, 0)
                setActiveSet(DEFAULT_SET)
            }
        }

        fun getActiveSet(): Int? {
            return prefs?.getInt(KEY_ACTIVE_SET, 0)
        }

        fun setActiveSet(id: Int) {
            prefs?.edit()?.putInt(KEY_ACTIVE_SET, id)?.apply()
        }
    }
}