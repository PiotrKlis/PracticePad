package com.piotr.practicepad.di.modules

import com.piotr.practicepad.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlin.time.ExperimentalTime

@Module
abstract class ActivityBindingModule {
    @OptIn(ExperimentalTime::class)
    @ContributesAndroidInjector(
        modules = [
            ExerciseModule::class,
            ExerciseListModule::class,
            MetronomeModule::class,
            TimerModule::class,
            PracticeStateModule::class,
            ExerciseSetDetailsModule::class,
            AddExerciseModule::class]
    )
    abstract fun mainActivity(): MainActivity
}
