package com.piotr.practicepad

import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.utils.AndroidStringProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PracticePad : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.initSharedPrefs(applicationContext)
        AndroidStringProvider.initializeStringProvider(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}