package com.piotr.practicepad.data.db

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefs @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_ACTIVE_SET = "active_set"
        const val DEFAULT_SET_ID: Int = 1000
    }

    fun getActiveSetId(): Int = prefs.getInt(KEY_ACTIVE_SET, DEFAULT_SET_ID)
    fun setActiveSetId(id: Int) = prefs.edit()?.putInt(KEY_ACTIVE_SET, id)?.apply()
    fun isSetActive(id: Int): Boolean = id == getActiveSetId()
}