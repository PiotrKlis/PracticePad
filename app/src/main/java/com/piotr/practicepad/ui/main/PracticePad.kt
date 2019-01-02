package com.piotr.practicepad.ui.main

import android.app.Application
import com.piotr.practicepad.ui.main.ExerciseList.ExercieSetDatabase

class PracticePad : Application() {

    override fun onCreate() {
        super.onCreate()
        initiliazeRoomDatabase(this)
    }

    private fun initiliazeRoomDatabase(applicationContext: Application) {
        ExercieSetDatabase.initializeDatabase(applicationContext)
    }
}