package com.piotr.practicepad.di.modules

import com.piotr.practicepad.views.addExerciseSet.AddExerciseSetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ExerciseAddModule {

    @ContributesAndroidInjector
    abstract fun contributesExerciseAddFragment(): AddExerciseSetFragment
}