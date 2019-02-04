package com.piotr.practicepad.ui.main

import android.app.Application

class PracticePad : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.initSharedPrefs(applicationContext)
    }
}