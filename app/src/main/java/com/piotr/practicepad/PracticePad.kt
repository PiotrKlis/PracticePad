package com.piotr.practicepad

import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.repository.EntityMapper
import com.piotr.practicepad.di.DaggerAppComponent
import com.piotr.practicepad.utils.AndroidResourceProvider
import com.piotr.practicepad.utils.AndroidStringProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PracticePad : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        AndroidStringProvider.initializeStringProvider(applicationContext)
        AndroidResourceProvider.init(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}
