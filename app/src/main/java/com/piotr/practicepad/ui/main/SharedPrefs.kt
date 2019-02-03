package com.piotr.practicepad.ui.main

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs() {

    companion object {
        var prefs: SharedPreferences? = null
        val KEY_ACTIVE_SET = "active_set"
        val DEFAULT_SET: Int = 0


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