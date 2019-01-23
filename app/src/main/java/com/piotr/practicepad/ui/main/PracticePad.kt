package com.piotr.practicepad.ui.main

import android.app.Application

class PracticePad : Application() {

    override fun onCreate() {
        super.onCreate()
        initiliazeRoomDatabase(this)
    }

    private fun initiliazeRoomDatabase(applicationContext: Application) {
        PracticePadDatabase.initializeDatabase(applicationContext)
    }
}