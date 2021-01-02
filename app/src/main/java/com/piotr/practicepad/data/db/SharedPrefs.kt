package com.piotr.practicepad.data.db

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefs @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_ACTIVE_SET = "active_set"
        const val KEY_IS_FIRST_APP_LAUNCH = "is_first_app_launch"
        const val DEFAULT_SET_ID: Int = 1000
    }

    fun getActiveSetId(): Int = prefs.getInt(KEY_ACTIVE_SET, DEFAULT_SET_ID)
    fun setActiveSetId(id: Int) = prefs.edit()?.putInt(KEY_ACTIVE_SET, id)?.apply()
    fun isSetActive(id: Int): Boolean = id == getActiveSetId()
    fun isFirstAppLaunch(): Boolean =  prefs.getBoolean(KEY_IS_FIRST_APP_LAUNCH, true)
    fun setFirstAppLaunch() = prefs.edit()?.putBoolean(KEY_IS_FIRST_APP_LAUNCH, false)?.apply()
}