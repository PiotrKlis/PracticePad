package com.piotr.practicepad

import android.content.Intent
import androidx.core.content.ContextCompat
import com.piotr.practicepad.di.DaggerAppComponent
import com.piotr.practicepad.metronome.MetronomeService
import com.piotr.practicepad.utils.AndroidResourceProvider
import com.piotr.practicepad.utils.AndroidStringProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.FlowPreview

class PracticePad : DaggerApplication() {
    @FlowPreview
    override fun onCreate() {
        super.onCreate()
        AndroidStringProvider.initializeStringProvider(applicationContext)
        AndroidResourceProvider.init(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}
