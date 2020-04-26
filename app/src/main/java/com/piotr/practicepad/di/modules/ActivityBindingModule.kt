package com.piotr.practicepad.di.modules

import com.piotr.practicepad.MainActivity
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
            PracticeStateModule::class]
    )
    abstract fun mainActivity(): MainActivity
}
