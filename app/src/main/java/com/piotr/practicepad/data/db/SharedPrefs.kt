package com.piotr.practicepad.data.db

import android.content.SharedPreferences
import javax.inject.Inject

private const val KEY_ACTIVE_SET = "active_set"
private const val DEFAULT_SET: Int = 0

class SharedPrefs @Inject constructor(private val prefs: SharedPreferences) {
    fun getActiveSetId(): Int = prefs.getInt(KEY_ACTIVE_SET, DEFAULT_SET)
    fun setActiveSetId(id: Int) = prefs.edit()?.putInt(KEY_ACTIVE_SET, id)?.apply()
    fun isSetActive(id: Int): Boolean = id == getActiveSetId()
}