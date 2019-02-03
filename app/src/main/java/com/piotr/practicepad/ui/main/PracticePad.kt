package com.piotr.practicepad.ui.main

import android.app.Application
import com.piotr.practicepad.ui.main.Room.PracticePadDatabase

class PracticePad : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.initSharedPrefs(applicationContext)
        initiliazeRoomDatabase(this)
    }

    private fun initiliazeRoomDatabase(applicationContext: Application) {
        PracticePadDatabase.initializeDatabase(applicationContext)
    }
}