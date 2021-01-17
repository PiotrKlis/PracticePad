package com.piotr.practicepad.di.modules

import com.piotr.practicepad.timers.ExerciseSetTimer
import com.piotr.practicepad.timers.ExerciseTimer
import com.piotr.practicepad.views.exercise.Practice
import dagger.Module
import dagger.Provides

@Module
class TimerModule {

    @Provides
    fun providesExerciseSetTimer(practice: Practice): ExerciseSetTimer = ExerciseSetTimer(practice)

    @Provides
    fun providesExerciseTimer(practice: Practice): ExerciseTimer = ExerciseTimer(practice)
}
