package com.piotr.practicepad.di.modules

import com.piotr.practicepad.MainActivity
import com.piotr.practicepad.data.db.SharedPrefs
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(
        modules = [
            ExerciseModule::class,
            ExerciseListModule::class,
            MetronomeModule::class,
            TimerModule::class,
            PracticeStateModule::class,
            ExerciseSetDetailsModule::class,
            ExerciseAddModule::class]
    )
    abstract fun mainActivity(): MainActivity
}
