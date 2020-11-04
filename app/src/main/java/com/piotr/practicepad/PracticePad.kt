package com.piotr.practicepad

import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.repository.ExerciseSetEntityMapper
import com.piotr.practicepad.di.DaggerAppComponent
import com.piotr.practicepad.utils.AndroidStringProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class PracticePad : DaggerApplication() {
    @Inject
    lateinit var exerciseSetEntityMapper: ExerciseSetEntityMapper

    override fun onCreate() {
        super.onCreate()
        AndroidStringProvider.initializeStringProvider(applicationContext)
        PracticePadRoomDatabase.buildDatabase(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}
