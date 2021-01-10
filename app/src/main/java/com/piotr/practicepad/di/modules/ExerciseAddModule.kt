package com.piotr.practicepad.di.modules

import com.piotr.practicepad.addExercise.ExerciseAddFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ExerciseAddModule {

    @ContributesAndroidInjector
    abstract fun contributesExerciseAddFragment(): ExerciseAddFragment
}