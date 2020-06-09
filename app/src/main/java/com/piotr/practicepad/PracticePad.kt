package com.piotr.practicepad

import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.di.DaggerAppComponent
import com.piotr.practicepad.utils.AndroidStringProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class PracticePad : DaggerApplication() {

    @Inject
    lateinit var db: PracticePadRoomDatabase

    override fun onCreate() {
        super.onCreate()
        AndroidStringProvider.initializeStringProvider(applicationContext)
        //TODO: Prepopulate data #1 https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}