package com.piotr.practicepad

import com.piotr.practicepad.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PracticePad : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}
