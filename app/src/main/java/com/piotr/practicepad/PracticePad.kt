package com.piotr.practicepad.ui.main

import android.app.Application
import com.piotr.practicepad.ui.main.data.db.SharedPrefs
import com.piotr.practicepad.ui.main.utils.AndroidStringProvider

class PracticePad : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.initSharedPrefs(applicationContext)
        AndroidStringProvider.initializeStringProvider(applicationContext)
    }
}