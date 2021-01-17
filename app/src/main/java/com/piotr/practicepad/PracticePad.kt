package com.piotr.practicepad

import com.piotr.practicepad.di.DaggerAppComponent
import com.piotr.practicepad.utils.AndroidResourceProvider
import com.piotr.practicepad.utils.AndroidStringProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PracticePad : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}
