package com.piotr.practicepad

import android.app.Application
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.utils.AndroidStringProvider

class PracticePad : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.initSharedPrefs(applicationContext)
        AndroidStringProvider.initializeStringProvider(applicationContext)
    }
}