package com.piotr.practicepad.di.modules

import com.piotr.practicepad.timers.ExerciseSetTimer
import com.piotr.practicepad.timers.ExerciseTimer
import dagger.Module
import dagger.Provides

@Module
class TimerModule {

    @Provides
    fun providesExerciseSetTimer(): ExerciseSetTimer = ExerciseSetTimer()

    @Provides
    fun providesExerciseTimer(): ExerciseTimer = ExerciseTimer()
}
