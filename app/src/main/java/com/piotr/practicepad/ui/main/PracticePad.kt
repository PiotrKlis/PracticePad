package com.piotr.practicepad.ui.main

import android.app.Application
import com.piotr.practicepad.ui.main.data.SharedPrefs

class PracticePad : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.initSharedPrefs(applicationContext)
    }
}